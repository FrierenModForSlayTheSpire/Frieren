package FrierenMod.cards.optionCards.magicFactors;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class Vigor extends AbstractMagicFactor {
    public static final String ID = ModInformation.makeID(Vigor.class.getSimpleName());

    public Vigor() {
        super(ID);
        this.factorRarity = FactorRarityType.COMMON;
        this.rewardAddCoefficient = 4;
    }

    @Override
    public void takeEffect() {
        this.addToBot(new ApplyPowerAction(p, p, new VigorPower(p, this.secondMagicNumber)));
    }
}
