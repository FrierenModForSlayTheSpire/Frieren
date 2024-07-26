package FrierenMod.cards.optionCards.magicFactors;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class BaseFactorGamma extends AbstractMagicFactor {
    public static final String ID = ModInformation.makeID(BaseFactorGamma.class.getSimpleName());

    public BaseFactorGamma() {
        super(ID);
        this.factorRarity = FactorRarityType.BASIC;
    }

    @Override
    public void takeEffect() {
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.secondMagicNumber)));
    }
}
