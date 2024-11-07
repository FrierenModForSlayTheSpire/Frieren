package FrierenMod.powers.FusionPower;

import FrierenMod.cardMods.DrawMod;
import FrierenMod.utils.ModInformation;

public class DrawCardFusionPower extends AbstractFusionPower {
    public static final String POWER_ID = ModInformation.makeID(DrawCardFusionPower.class.getSimpleName());

    public DrawCardFusionPower(int amount) {
        super(POWER_ID, amount);
        this.modifier = new DrawMod(amount);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}
