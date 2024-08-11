package FrierenMod.cards.magicItems.props;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.effects.ExhaustMagicItemEffect;
import FrierenMod.gameHelpers.CardPoolHelper;
import FrierenMod.patches.fields.MagicDeckField;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class TransmutationCircle extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(TransmutationCircle.class.getSimpleName());

    public TransmutationCircle() {
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
            if (((AbstractMagicItem) c).magicItemRarity == MagicItemRarity.PROP)
                return false;
        }
        for (AbstractCard chosenCard : chosenCards) {
            AbstractMagicItem oldItem = (AbstractMagicItem) chosenCard;
            AbstractDungeon.topLevelEffects.add(new ExhaustMagicItemEffect(oldItem));
            AbstractMagicItem newItem = (AbstractMagicItem) CardPoolHelper.getRandomMagicItem(oldItem.magicItemRarity);
            newItem.currentSlot = oldItem.currentSlot;
            MagicDeckField.getDeck().addToTop(newItem);
            newItem.superFlash();
        }
        return true;
    }
}
