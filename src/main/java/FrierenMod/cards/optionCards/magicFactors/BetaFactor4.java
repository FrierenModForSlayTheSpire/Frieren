package FrierenMod.cards.optionCards.magicFactors;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;

public class BetaFactor4 extends AbstractMagicFactor {
    public static final String ID = ModInformation.makeID(BetaFactor4.class.getSimpleName());

    public BetaFactor4() {
        super(ID);
        this.factorRarity = FactorRarityType.COMMON;
        this.rewardMultipleCoefficient = 3;
    }

    @Override
    public void takeEffect() {
       addToBot(new DamageAllEnemiesAction(p, this.secondMagicNumber, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }
}
