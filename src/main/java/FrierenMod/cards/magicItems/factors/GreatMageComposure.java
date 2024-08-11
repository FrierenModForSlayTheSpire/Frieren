package FrierenMod.cards.magicItems.factors;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.powers.ChantWithoutManaTimesPower;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;

public class GreatMageComposure extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(GreatMageComposure.class.getSimpleName());

    public GreatMageComposure() {
        super(ID);
        loadRarity(MagicItemRarity.RARE);
        this.manaNeedMultipleCoefficient = 3;
    }

    @Override
    public void takeEffect() {
        addToBot(new ApplyPowerAction(this.p, this.p, new ChantWithoutManaTimesPower(this.p, secondMagicNumber), secondMagicNumber));
    }
}
