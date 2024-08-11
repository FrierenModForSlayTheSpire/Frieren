package FrierenMod.cards.magicItems.factors;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;

public class PeacefulDays extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(PeacefulDays.class.getSimpleName());

    public PeacefulDays() {
        super(ID);
        loadRarity(MagicItemRarity.RARE);
        this.manaNeedAddCoefficient = 1;
    }

    @Override
    public void takeEffect() {
        this.addToBot(new DrawCardAction(secondMagicNumber));
    }
}
