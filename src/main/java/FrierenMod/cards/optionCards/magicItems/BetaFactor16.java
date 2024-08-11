package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;

public class BetaFactor16 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor16.class.getSimpleName());

    public BetaFactor16() {
        super(ID);
        loadRarity(MagicItemRarity.RARE);
        this.manaNeedMultipleCoefficient = 2;
    }

    @Override
    public void takeEffect() {
        this.addToBot(new GainEnergyAction(secondMagicNumber));
    }
}
