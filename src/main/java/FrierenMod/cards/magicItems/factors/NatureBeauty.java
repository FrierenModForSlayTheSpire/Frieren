package FrierenMod.cards.magicItems.factors;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.EnergizedPower;

public class NatureBeauty extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(NatureBeauty.class.getSimpleName());

    public NatureBeauty() {
        super(ID);
        loadRarity(MagicItemRarity.UNCOMMON);
        this.manaNeedAddCoefficient = 2;
    }

    @Override
    public void takeEffect() {
        addToBot(new ApplyPowerAction(p, p,new EnergizedPower(p, secondMagicNumber), secondMagicNumber));
    }
}
