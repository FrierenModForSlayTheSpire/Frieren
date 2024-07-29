package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class BaseFactorGamma extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BaseFactorGamma.class.getSimpleName());

    public BaseFactorGamma() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.BASIC;
    }

    @Override
    public void takeEffect() {
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.secondMagicNumber)));
    }
}
