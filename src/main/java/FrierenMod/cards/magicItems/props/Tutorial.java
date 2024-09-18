package FrierenMod.cards.magicItems.props;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public class Tutorial extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(Tutorial.class.getSimpleName());

    public Tutorial() {
        super(ID);
        loadRarity(MagicItemRarity.PROP);
        this.propCanChooseMaxAmt = 1;
        this.tags.add(Enum.NEVER_DROP);
    }

    public boolean propTakeEffect(ArrayList<AbstractCard> chosenCards) {
        for (AbstractCard c : chosenCards) {
            if (c == this)
                return false;
        }
        return chosenCards.size() == propCanChooseMaxAmt;
    }
}
