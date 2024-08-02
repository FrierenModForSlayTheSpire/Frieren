package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class BetaFactor5 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor5.class.getSimpleName());

    public BetaFactor5() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.UNCOMMON;
    }

    @Override
    public void takeEffect() {
        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.secondMagicNumber)));
    }
}
