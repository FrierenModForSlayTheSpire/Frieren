package FrierenMod.cards.optionCards.magicFactors;

import FrierenMod.actions.ExhaustManaInCardGroupAction;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;

public class BaseFactorAlpha extends AbstractMagicFactor {
    public static final String ID = ModInformation.makeID(BaseFactorAlpha.class.getSimpleName());

    public BaseFactorAlpha() {
        super(ID);
        this.factorRarity = FactorRarityType.BASIC;
    }

    @Override
    public void takeEffect() {
        if (this.magicNumber > 0)
            this.addToBot(new ExhaustManaInCardGroupAction(magicNumber,currentSlot));
        for (int i = 0; i < 2; i++) {
            this.addToBot(new GainBlockAction(p, p, this.secondMagicNumber));
        }
    }
}
