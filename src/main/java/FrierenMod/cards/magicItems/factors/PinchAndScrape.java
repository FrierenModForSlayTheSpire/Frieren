package FrierenMod.cards.magicItems.factors;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.powers.SynchroWithoutManaPower;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;

public class PinchAndScrape extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(PinchAndScrape.class.getSimpleName());

    public PinchAndScrape() {
        super(ID);
        loadRarity(MagicItemRarity.UNCOMMON);
        this.rewardMultipleCoefficient = 2;
    }

    @Override
    public void takeEffect() {
        addToBot(new ApplyPowerAction(this.p, this.p, new SynchroWithoutManaPower(this.p, secondMagicNumber), secondMagicNumber));
    }
}
