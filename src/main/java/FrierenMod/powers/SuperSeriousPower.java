package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class SuperSeriousPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(SuperSeriousPower.class.getSimpleName());

    public SuperSeriousPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}