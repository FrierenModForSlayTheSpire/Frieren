package FrierenMod.cards.magicItems.factors;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.powers.BlurPower;

public class BarrierMagic extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BarrierMagic.class.getSimpleName());

    public BarrierMagic() {
        super(ID);
        loadRarity(MagicItemRarity.UNCOMMON);
        this.manaNeedAddCoefficient = 2;
    }

    @Override
    public void takeEffect() {
        this.addToBot(new GainBlockAction(p, secondMagicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new BlurPower(p, secondMagicNumber)));
    }
}
