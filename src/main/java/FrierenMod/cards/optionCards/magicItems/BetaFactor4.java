package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;

public class BetaFactor4 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor4.class.getSimpleName());

    public BetaFactor4() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.COMMON;
        this.rewardMultipleCoefficient = 3;
    }

    @Override
    public void takeEffect() {
        addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(secondMagicNumber, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
    }
}
