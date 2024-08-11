package FrierenMod.cards.magicItems.props;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.patches.fields.RandomField;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.*;

public class MysticBox extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(MysticBox.class.getSimpleName());

    public MysticBox() {
        super(ID);
        loadRarity(MagicItemRarity.PROP);
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
        List<Integer> newSlotNumber = Arrays.asList(0, 1, 2);
        Collections.shuffle(newSlotNumber, new Random(RandomField.getMagicItemRandomRng().randomLong()));
        for (int i = 0; i < propCanChooseMaxAmt; i++){
            AbstractMagicItem oldItem = CombatHelper.getLoadedMagicFactor()[i];
            oldItem.currentSlot = -1;
            oldItem.superFlash();
        }
        for (int i = 0; i < propCanChooseMaxAmt; i++){
            AbstractMagicItem newItem = ((AbstractMagicItem) chosenCards.get(i));
            newItem.currentSlot = newSlotNumber.get(i);
            newItem.superFlash();
        }
        return true;
    }
}
