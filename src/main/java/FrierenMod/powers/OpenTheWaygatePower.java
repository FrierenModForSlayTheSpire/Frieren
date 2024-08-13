package FrierenMod.powers;

import FrierenMod.cards.tempCards.Mana;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

public class OpenTheWaygatePower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(OpenTheWaygatePower.class.getSimpleName());
    public int originAmt;

    public OpenTheWaygatePower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
        this.updateDescription();
        this.originAmt = amount;
    }

    @Override
    public void stackPower(int stackAmount) {

    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof Mana) {
            this.flash();
            this.amount = this.originAmt - CombatHelper.getContinualSynchroTimes(card);
            this.updateDescription();
            if (this.amount <= 0){
                addToBot(new VFXAction(new WhirlwindEffect(new Color(1.0F, 0.9F, 0.4F, 1.0F), true)));
                addToBot(new SkipEnemiesTurnAction());
                addToBot(new PressEndTurnButtonAction());
                addToBot(new RemoveSpecificPowerAction(owner, owner, this));
            }
        } else {
            this.amount = originAmt;
            this.updateDescription();
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.amount = originAmt;
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}