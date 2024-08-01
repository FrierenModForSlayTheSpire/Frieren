package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.effects.ExhaustMagicItemEffect;
import FrierenMod.patches.fields.MagicDeckField;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class BetaProp7 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaProp7.class.getSimpleName());

    public BetaProp7() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.PROP;
        this.propCanChooseMaxAmt = 2;
        this.cardsToPreview = new BetaProp1();
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
        for(AbstractCard c: chosenCards)
            AbstractDungeon.topLevelEffects.add(new ExhaustMagicItemEffect(c));
        for (int i = 0; i < 2; i++) {
            AbstractCard c = new BetaProp1();
            MagicDeckField.getDeck().addToTop(c);
            c.superFlash();
        }
        return true;
    }
}
