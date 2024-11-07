package FrierenMod.powers.FusionPower;

import FrierenMod.cardMods.ConcentrationMod;
import FrierenMod.utils.ModInformation;

public class ConcentrationFusionPower extends AbstractFusionPower {
    public static final String POWER_ID = ModInformation.makeID(ConcentrationFusionPower.class.getSimpleName());

    public ConcentrationFusionPower(int amount) {
        super(POWER_ID, amount);
        this.modifier = new ConcentrationMod(amount);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}
