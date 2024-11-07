package FrierenMod.powers.FusionPower;

import FrierenMod.cardMods.CopySelfToDiscardMod;
import FrierenMod.utils.ModInformation;

public class CopySelfToDiscardFusionPower extends AbstractFusionPower {
    public static final String POWER_ID = ModInformation.makeID(CopySelfToDiscardFusionPower.class.getSimpleName());

    public CopySelfToDiscardFusionPower(int amount) {
        super(POWER_ID, amount);
        this.modifier = new CopySelfToDiscardMod(amount);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}
