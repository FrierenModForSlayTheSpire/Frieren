package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.StrikeUpPower;

public class BetaFactor18 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor18.class.getSimpleName());

    public BetaFactor18() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.COMMON;
        this.rewardAddCoefficient = 1;
    }

    @Override
    public void takeEffect() {
        addToBot(new ApplyPowerAction(p, p, new StrikeUpPower(p, secondMagicNumber)));
    }
}
