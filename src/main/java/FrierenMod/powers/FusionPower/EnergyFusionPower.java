package FrierenMod.powers.FusionPower;

import FrierenMod.cardMods.EnergyMod;
import FrierenMod.utils.ModInformation;

public class EnergyFusionPower extends AbstractFusionPower {
    public static final String POWER_ID = ModInformation.makeID(EnergyFusionPower.class.getSimpleName());

    public EnergyFusionPower(int amount) {
        super(POWER_ID, amount);
        this.modifier = new EnergyMod(amount);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}
