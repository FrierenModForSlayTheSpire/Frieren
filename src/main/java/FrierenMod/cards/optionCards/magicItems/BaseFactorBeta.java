package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;

public class BaseFactorBeta extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BaseFactorBeta.class.getSimpleName());

    public BaseFactorBeta() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.BASIC;
    }

    @Override
    public void takeEffect() {
        this.addToBot(new GainEnergyAction(secondMagicNumber));
    }
}
