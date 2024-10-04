package FrierenMod.cards.magicItems.props;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.effects.ExhaustMagicItemEffect;
import FrierenMod.gameHelpers.SlotBgHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class SeinNote extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(SeinNote.class.getSimpleName());

    public SeinNote() {
        super(ID);
        loadRarity(MagicItemRarity.PROP);
        this.propCanChooseMaxAmt = 2;
    }

    public boolean propTakeEffect(ArrayList<AbstractCard> chosenCards) {
        if (chosenCards.size() != propCanChooseMaxAmt)
            return false;
        for (AbstractCard c : chosenCards) {
            if (!(c instanceof AbstractMagicItem))
                return false;
            if (c == this)
                return false;
            if (((AbstractMagicItem) c).currentSlot > -1)
                return false;
        }
        for (AbstractCard c : chosenCards)
            AbstractDungeon.topLevelEffects.add(new ExhaustMagicItemEffect(c));
        p.heal(10, true);
        SlotBgHelper.unlockANewSlot("4013");
        return true;
    }
}
