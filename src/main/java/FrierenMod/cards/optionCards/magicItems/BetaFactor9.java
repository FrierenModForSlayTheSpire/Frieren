package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;

public class BetaFactor9 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor9.class.getSimpleName());

    public BetaFactor9() {
        super(ID);
        loadRarity(MagicItemRarity.RARE);
        this.manaNeedAddCoefficient = 1;
    }

    @Override
    public void takeEffect() {
        this.addToBot(new DrawCardAction(secondMagicNumber));
    }
}
