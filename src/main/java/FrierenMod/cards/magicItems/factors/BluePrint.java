package FrierenMod.cards.magicItems.factors;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.utils.ModInformation;

public class BluePrint extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BluePrint.class.getSimpleName());

    public BluePrint() {
        super(ID);
        loadRarity(MagicItemRarity.UNCOMMON);
    }
}
