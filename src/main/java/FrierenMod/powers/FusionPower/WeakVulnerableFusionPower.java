package FrierenMod.powers.FusionPower;

import FrierenMod.cardMods.WeakVulnerableMod;
import FrierenMod.utils.ModInformation;

public class WeakVulnerableFusionPower extends AbstractFusionPower {
    public static final String POWER_ID = ModInformation.makeID(WeakVulnerableFusionPower.class.getSimpleName());

    public WeakVulnerableFusionPower(int amount) {
        super(POWER_ID, amount);
        this.modifier = new WeakVulnerableMod(amount);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}
