package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class DancingPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(DancingPower.class.getSimpleName());

    public DancingPower(AbstractCreature owner) {
        super(POWER_ID, owner, -1, PowerType.BUFF);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}
