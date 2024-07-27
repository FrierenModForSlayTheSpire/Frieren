package FrierenMod.cards.optionCards.magicFactors;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.MetallicizePower;

public class BetaFactor2 extends AbstractMagicFactor {
    public static final String ID = ModInformation.makeID(BetaFactor2.class.getSimpleName());

    public BetaFactor2() {
        super(ID);
        this.factorRarity = FactorRarityType.COMMON;
    }

    @Override
    public void takeEffect() {
        this.addToBot(new ApplyPowerAction(p, p, new MetallicizePower(p, this.secondMagicNumber)));
    }
}
