package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.EnergizedPower;

public class BetaFactor3 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor3.class.getSimpleName());

    public BetaFactor3() {
        super(ID);
        this.factorRarity = FactorRarityType.COMMON;
        this.rewardAddCoefficient = 1;
    }

    @Override
    public void takeEffect() {
        addToBot(new ApplyPowerAction(p, p,new EnergizedPower(p, secondMagicNumber), secondMagicNumber));
    }
}
