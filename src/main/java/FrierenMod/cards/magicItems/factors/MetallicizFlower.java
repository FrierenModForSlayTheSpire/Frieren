package FrierenMod.cards.magicItems.factors;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.MetallicizePower;

public class MetallicizFlower extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(MetallicizFlower.class.getSimpleName());

    public MetallicizFlower() {
        super(ID);
        loadRarity(MagicItemRarity.COMMON);
    }

    @Override
    public void takeEffect() {
        this.addToBot(new ApplyPowerAction(p, p, new MetallicizePower(p, this.secondMagicNumber)));
    }
}
