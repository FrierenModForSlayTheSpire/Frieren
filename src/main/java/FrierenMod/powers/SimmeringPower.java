package FrierenMod.powers;

import FrierenMod.actions.SimmeringPowerAction;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class SimmeringPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(SimmeringPower.class.getSimpleName());

    public SimmeringPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
        this.updateDescription();
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK)
            this.addToBot(new SimmeringPowerAction(this.amount));
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}
