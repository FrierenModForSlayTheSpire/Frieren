package FrierenMod.powers;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class WeakenedChantPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(WeakenedChantPower.class.getSimpleName());

    public WeakenedChantPower(AbstractCreature owner, int amt) {
        super(POWER_ID, owner, amt, PowerType.DEBUFF);
        this.updateDescription();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractBaseCard && ((AbstractBaseCard) card).isChantCard) {
            this.flash();
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0],this.amount);
    }
}