package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class BetaFactor17 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor17.class.getSimpleName());

    public BetaFactor17() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.COMMON;
    }

    @Override
    public void takeEffect() {
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.secondMagicNumber)));
    }
}
