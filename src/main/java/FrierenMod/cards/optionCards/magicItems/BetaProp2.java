package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.gameHelpers.CardPoolHelper;
import FrierenMod.patches.fields.MagicDeckField;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public class BetaProp2 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaProp2.class.getSimpleName());

    public BetaProp2() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.PROP;
        this.propCanChooseMaxAmt = 0;
    }

    public boolean propTakeEffect(ArrayList<AbstractCard> chosenCards) {
        for (int i = 0; i < 2; i++) {
            AbstractCard c = CardPoolHelper.getRandomCard(CardPoolHelper.PoolType.MAGIC_FACTOR);
            MagicDeckField.getDeck().addToTop(c);
            c.superFlash();
        }
        return true;
    }
}
