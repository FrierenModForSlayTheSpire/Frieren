package FrierenMod.powers;

import FrierenMod.cards.tempCards.Mana;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class SynchroWithoutManaPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(SynchroWithoutManaPower.class.getSimpleName());

    public SynchroWithoutManaPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
        this.updateDescription();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof Mana) {
            this.flash();
            this.amount--;
            this.updateDescription();
            if (this.amount <= 0)
                this.addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        }
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}