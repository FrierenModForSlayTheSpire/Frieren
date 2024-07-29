package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.powers.BlurPower;

public class BetaFactor12 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor12.class.getSimpleName());

    public BetaFactor12() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.UNCOMMON;
        this.manaNeedMultipleCoefficient = 2;
    }

    @Override
    public void takeEffect() {
        this.addToBot(new GainBlockAction(p, secondMagicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new BlurPower(p, secondMagicNumber)));
    }
}
