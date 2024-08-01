package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class BetaFactor1 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor1.class.getSimpleName());

    public BetaFactor1() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.COMMON;
        this.rewardAddCoefficient = 3;
    }

    @Override
    public void takeEffect() {
        this.addToBot(new ApplyPowerAction(p, p, new VigorPower(p, this.secondMagicNumber)));
    }
}
