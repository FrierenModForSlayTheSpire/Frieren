package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;

public class BetaFactor24 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor24.class.getSimpleName());

    public BetaFactor24() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.UNCOMMON;
    }
}
