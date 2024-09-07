package FrierenMod.relics;

import FrierenMod.utils.ModInformation;

public class FlammeGrimoire extends AbstractBaseRelic {
    public static final String ID = ModInformation.makeID(FlammeGrimoire.class.getSimpleName());

    public FlammeGrimoire() {
        super(ID, RelicTier.SPECIAL);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
