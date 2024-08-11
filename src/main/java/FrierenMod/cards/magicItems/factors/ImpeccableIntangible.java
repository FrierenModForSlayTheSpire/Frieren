package FrierenMod.cards.magicItems.factors;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

public class ImpeccableIntangible extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(ImpeccableIntangible.class.getSimpleName());

    public ImpeccableIntangible() {
        super(ID);
        loadRarity(MagicItemRarity.RARE);
        this.manaNeedMultipleCoefficient = 6;
    }

    @Override
    public void takeEffect() {
        this.addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, secondMagicNumber)));
    }
}
