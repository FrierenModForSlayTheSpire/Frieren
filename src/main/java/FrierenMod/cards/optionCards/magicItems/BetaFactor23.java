package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.powers.SynchroWithoutManaPower;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;

public class BetaFactor23 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor23.class.getSimpleName());

    public BetaFactor23() {
        super(ID);
        loadRarity(MagicItemRarity.UNCOMMON);
        this.rewardMultipleCoefficient = 2;
    }

    @Override
    public void takeEffect() {
        addToBot(new ApplyPowerAction(this.p, this.p, new SynchroWithoutManaPower(this.p, secondMagicNumber), secondMagicNumber));
    }
}
