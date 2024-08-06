package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.watcher.MantraPower;

public class BetaFactor14 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor14.class.getSimpleName());

    public BetaFactor14() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.RARE;
        this.manaNeedAddCoefficient = 1;
    }

    @Override
    public void takeEffect() {
        this.addToBot(new ApplyPowerAction(p, p, new MantraPower(p, secondMagicNumber)));
    }
}
