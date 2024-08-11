package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public class BetaProp8 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaProp8.class.getSimpleName());

    public BetaProp8() {
        super(ID);
        loadRarity(MagicItemRarity.PROP);
        this.propCanChooseMaxAmt = 2;
    }

    public boolean propTakeEffect(ArrayList<AbstractCard> chosenCards) {
        AbstractMagicItem item1 = null;
        AbstractMagicItem item2 = null;
        if (chosenCards.get(0) instanceof AbstractMagicItem)
            item1 = (AbstractMagicItem) chosenCards.get(0);
        if (chosenCards.get(1) instanceof AbstractMagicItem)
            item2 = (AbstractMagicItem) chosenCards.get(1);
        if (item1 == null || item2 == null)
            return false;
        if (item1.magicItemRarity == MagicItemRarity.PROP || item2.magicItemRarity == MagicItemRarity.PROP) {
            return false;
        }
        if (item1.currentSlot == -1 || item2.currentSlot == -1) {
            return false;
        }
        if (item1.currentSlot == item2.currentSlot) {
            return false;
        }
        int tmp = item1.currentSlot;
        item1.currentSlot = item2.currentSlot;
        item2.currentSlot = tmp;
        item1.superFlash();
        item2.superFlash();
        return true;
    }
}
