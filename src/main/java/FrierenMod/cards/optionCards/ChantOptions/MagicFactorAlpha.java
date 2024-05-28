package FrierenMod.cards.optionCards.ChantOptions;

import FrierenMod.utils.ModInformation;

public class MagicFactorAlpha extends AbstractChantOption {
    public static final String ID = ModInformation.makeID(MagicFactorAlpha.class.getSimpleName());

    public MagicFactorAlpha() {
        super(ID);
    }

    public MagicFactorAlpha(ShowPlaceType type) {
        super(ID, type);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.secondMagicNumber = this.baseSecondMagicNumber = 3;
        this.block = this.baseBlock = 3;
    }
}
