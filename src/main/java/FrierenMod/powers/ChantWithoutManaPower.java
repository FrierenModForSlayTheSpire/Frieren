package FrierenMod.powers;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class ChantWithoutManaPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(ChantWithoutManaPower.class.getSimpleName());
    public ChantWithoutManaPower(AbstractCreature owner) {
        super(POWER_ID, owner, PowerType.BUFF);
        this.updateDescription();
    }
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(AbstractBaseCard.Enum.CHANT)) {
            this.flash();
        }
    }
    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }
    public void updateDescription() {
        this.description = String.format(descriptions[0]);
    }
}