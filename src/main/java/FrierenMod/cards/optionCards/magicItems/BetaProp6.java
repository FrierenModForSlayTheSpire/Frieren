package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.effects.ExhaustMagicItemEffect;
import FrierenMod.gameHelpers.CardPoolHelper;
import FrierenMod.patches.fields.MagicDeckField;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class BetaProp6 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaProp6.class.getSimpleName());

    public BetaProp6() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.PROP;
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
        for(AbstractCard c:chosenCards){
            if(c instanceof AbstractMagicItem){
                AbstractDungeon.topLevelEffects.add(new ExhaustMagicItemEffect(c));
                AbstractMagicItem newItem = (AbstractMagicItem) CardPoolHelper.getRandomMagicItem(((AbstractMagicItem) c).magicItemRarity);
                newItem.currentSlot = ((AbstractMagicItem) c).currentSlot;
                MagicDeckField.getDeck().addToTop(newItem);
                newItem.superFlash();
            }
        }
        return true;
    }
}
