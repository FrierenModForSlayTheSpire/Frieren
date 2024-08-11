package FrierenMod.cards.magicItems.factors;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.PhantasmalPower;

public class SpellBoost extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(SpellBoost.class.getSimpleName());

    public SpellBoost() {
        super(ID);
        loadRarity(MagicItemRarity.UNCOMMON);
        this.manaNeedMultipleCoefficient = 4;
    }

    @Override
    public void takeEffect() {
        this.addToBot(new ApplyPowerAction(p, p, new PhantasmalPower(p, this.secondMagicNumber)));
    }
}
