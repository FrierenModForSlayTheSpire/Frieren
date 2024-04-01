package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class ConcentrationPower extends AbstractBasePower{
    public static final String POWER_ID = ModInformation.makeID(ConcentrationPower.class.getSimpleName());
    public ConcentrationPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        this.amount--;
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0]);
    }
}
