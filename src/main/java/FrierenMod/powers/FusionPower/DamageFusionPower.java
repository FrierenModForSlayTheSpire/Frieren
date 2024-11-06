package FrierenMod.powers.FusionPower;

import FrierenMod.utils.ModInformation;

public class DamageFusionPower extends AbstractFusionPower {
    public static final String POWER_ID = ModInformation.makeID(DamageFusionPower.class.getSimpleName());

    public DamageFusionPower(int amount) {
        super(POWER_ID, amount);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}
