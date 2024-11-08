package FrierenMod.powers.FusionPower;

import FrierenMod.cardMods.StrengthFusionMod;
import FrierenMod.utils.ModInformation;

public class StrengthFusionPowerFusionPower extends AbstractFusionPower {
    public static final String POWER_ID = ModInformation.makeID(StrengthFusionPowerFusionPower.class.getSimpleName());

    public StrengthFusionPowerFusionPower(int amount) {
        super(POWER_ID, amount);
        this.modifier = new StrengthFusionMod(amount);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}
