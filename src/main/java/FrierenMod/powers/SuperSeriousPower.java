package FrierenMod.powers;

import FrierenMod.actions.ModifyPowerStackAmtAction;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SuperSeriousPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(SuperSeriousPower.class.getSimpleName());

    public SuperSeriousPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (CombatHelper.getConcentrationPowerAmt() == 0){
            AbstractPlayer p = AbstractDungeon.player;
            this.addToBot(new ApplyPowerAction(p, p, new ConcentrationPower(p, 1)));
            this.addToBot(new ModifyPowerStackAmtAction(this, -1,true));
        }
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}