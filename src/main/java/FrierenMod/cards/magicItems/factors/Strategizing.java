package FrierenMod.cards.magicItems.factors;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;

public class Strategizing extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(Strategizing.class.getSimpleName());

    public Strategizing() {
        super(ID);
        loadRarity(MagicItemRarity.RARE);
        this.manaNeedMultipleCoefficient = 2;
    }

    @Override
    public void takeEffect() {
        this.addToBot(new GainEnergyAction(secondMagicNumber));
    }
}
