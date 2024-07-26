package FrierenMod.cards.optionCards.magicFactors;

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
        for (int i = 0; i < 2; i++) {
            this.addToBot(new GainBlockAction(p, p, this.secondMagicNumber));
        }
    }
}
