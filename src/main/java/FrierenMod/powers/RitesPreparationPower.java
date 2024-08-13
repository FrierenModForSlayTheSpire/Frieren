package FrierenMod.powers;

import FrierenMod.cards.tempCards.Mana;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class RitesPreparationPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(RitesPreparationPower.class.getSimpleName());

    public RitesPreparationPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
        this.updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof Mana) {
            this.flash();
            this.amount--;
            this.addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(6, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_VERTICAL, true));
            this.updateDescription();
            if (this.amount <= 0)
                this.addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        }
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}
