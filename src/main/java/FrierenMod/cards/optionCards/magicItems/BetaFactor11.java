package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.IntangiblePower;

public class BetaFactor11 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor11.class.getSimpleName());

    public BetaFactor11() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.RARE;
        this.manaNeedMultipleCoefficient = 6;
    }

    @Override
    public void takeEffect() {
        this.addToBot(new ApplyPowerAction(p, p, new IntangiblePower(p, secondMagicNumber)));
    }
}
