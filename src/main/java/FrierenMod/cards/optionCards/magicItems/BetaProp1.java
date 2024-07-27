package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;

public class BetaProp1 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaProp1.class.getSimpleName());

    public BetaProp1() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.PROP;
    }

    public boolean propTakeEffect(AbstractMagicItem factor1, AbstractMagicItem factor2) {
        if (factor1.magicItemRarity == MagicItemRarity.PROP || factor2.magicItemRarity == MagicItemRarity.PROP) {
            return false;
        }
        if (factor1.currentSlot == -1 && factor2.currentSlot == -1) {
            return false;
        }
        if (factor1.currentSlot > -1 && factor2.currentSlot > -1){
            return false;
        }
        if (factor1.currentSlot == factor2.currentSlot) {
            return false;
        }
        int tmp = factor1.currentSlot;
        factor1.currentSlot = factor2.currentSlot;
        factor2.currentSlot = tmp;
        return true;
    }
}
