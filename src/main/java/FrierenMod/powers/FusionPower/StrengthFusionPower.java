package FrierenMod.powers.FusionPower;

import FrierenMod.cardMods.StrengthMod;
import FrierenMod.utils.ModInformation;

public class StrengthFusionPower extends AbstractFusionPower {
    public static final String POWER_ID = ModInformation.makeID(StrengthFusionPower.class.getSimpleName());

    public StrengthFusionPower(int amount) {
        super(POWER_ID, amount);
        this.modifier = new StrengthMod(amount);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}
