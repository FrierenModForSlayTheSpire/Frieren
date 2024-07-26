package FrierenMod.cards.optionCards.magicFactors;

import FrierenMod.actions.ExhaustManaInCardGroupAction;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;

public class BaseFactorBeta extends AbstractMagicFactor {
    public static final String ID = ModInformation.makeID(BaseFactorBeta.class.getSimpleName());

    public BaseFactorBeta() {
        super(ID);
        this.factorRarity = FactorRarityType.BASIC;
    }

    @Override
    public void takeEffect() {
        if (magicNumber > 0)
            this.addToBot(new ExhaustManaInCardGroupAction(magicNumber, currentSlot));
        this.addToBot(new GainEnergyAction(secondMagicNumber));
    }
}
