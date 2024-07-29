package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.gameHelpers.MagicItemHelper;
import FrierenMod.patches.fields.RandomField;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.*;

public class BetaProp4 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaProp4.class.getSimpleName());

    public BetaProp4() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.PROP;
        this.propCanChooseMaxAmt = 3;
    }

    public boolean propTakeEffect(ArrayList<AbstractCard> chosenCards) {
        if (chosenCards.size() != propCanChooseMaxAmt)
            return false;
        for (AbstractCard c : chosenCards) {
            if (!(c instanceof AbstractMagicItem))
                return false;
            if (((AbstractMagicItem) c).magicItemRarity == MagicItemRarity.PROP)
                return false;
            if (((AbstractMagicItem) c).currentSlot > -1)
                return false;
        }
        for (AbstractMagicItem c : CombatHelper.getChantChoices()) {
            c.currentSlot = -1;
            c.superFlash();
        }
        List<Integer> newSlotNumber = Arrays.asList(0, 1, 2);
        Collections.shuffle(newSlotNumber, new Random(RandomField.getMagicItemRandomRng().randomLong()));
        for (int i = 0; i < propCanChooseMaxAmt; i++){
            AbstractMagicItem newItem = ((AbstractMagicItem) chosenCards.get(i));
            newItem.currentSlot = newSlotNumber.get(i);
            newItem.superFlash();
            AbstractMagicItem oldItem = MagicItemHelper.getLoadedMagicFactor()[i];
            oldItem.currentSlot = -1;
            oldItem.superFlash();
        }
        return true;
    }
}
