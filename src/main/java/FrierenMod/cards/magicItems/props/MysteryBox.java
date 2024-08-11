package FrierenMod.cards.magicItems.props;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.gameHelpers.CardPoolHelper;
import FrierenMod.patches.fields.MagicDeckField;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public class MysteryBox extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(MysteryBox.class.getSimpleName());

    public MysteryBox() {
        super(ID);
        loadRarity(MagicItemRarity.PROP);
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
