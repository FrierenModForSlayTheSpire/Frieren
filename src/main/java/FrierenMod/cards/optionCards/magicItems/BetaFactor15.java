package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;

public class BetaFactor15 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor15.class.getSimpleName());

    public BetaFactor15() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.COMMON;
        this.rewardAddCoefficient = 2;
    }

    @Override
    public void takeEffect() {
        this.addToBot(new GainBlockAction(p, p, this.secondMagicNumber));
    }
}
