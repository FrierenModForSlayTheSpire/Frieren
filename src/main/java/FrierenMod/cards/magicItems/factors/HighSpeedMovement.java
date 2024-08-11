package FrierenMod.cards.magicItems.factors;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class HighSpeedMovement extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(HighSpeedMovement.class.getSimpleName());

    public HighSpeedMovement() {
        super(ID);
        loadRarity(MagicItemRarity.UNCOMMON);
    }

    @Override
    public void takeEffect() {
        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.secondMagicNumber)));
    }
}
