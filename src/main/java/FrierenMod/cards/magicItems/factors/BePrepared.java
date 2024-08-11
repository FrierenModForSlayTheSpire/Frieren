package FrierenMod.cards.magicItems.factors;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class BePrepared extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BePrepared.class.getSimpleName());

    public BePrepared() {
        super(ID);
        loadRarity(MagicItemRarity.COMMON);
        this.rewardAddCoefficient = 3;
    }

    @Override
    public void takeEffect() {
        this.addToBot(new ApplyPowerAction(p, p, new VigorPower(p, this.secondMagicNumber)));
    }
}
