package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.powers.ChantWithoutManaTimesPower;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;

public class BetaFactor22 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor22.class.getSimpleName());

    public BetaFactor22() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.RARE;
        this.manaNeedMultipleCoefficient = 3;
    }

    @Override
    public void takeEffect() {
        addToBot(new ApplyPowerAction(this.p, this.p, new ChantWithoutManaTimesPower(this.p, secondMagicNumber), secondMagicNumber));
    }
}
