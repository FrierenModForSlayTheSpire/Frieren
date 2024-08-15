package FrierenMod.powers;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SynchroWithoutManaPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(SynchroWithoutManaPower.class.getSimpleName());

    public SynchroWithoutManaPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
        this.updateDescription();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.hasTag(AbstractBaseCard.Enum.SYNCHRO)) {
            this.flash();
            this.amount--;
            card.exhaust = false;
            this.updateDescription();
            if (this.amount <= 0)
                this.addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        }
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}