package FrierenMod.cards.magicItems.factors;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.utils.ModInformation;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;

@AutoAdd.Ignore
public class BloodSpell extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BloodSpell.class.getSimpleName());

    public BloodSpell() {
        super(ID);
        loadRarity(MagicItemRarity.UNCOMMON);
        this.manaNeedAddCoefficient = -2;
        this.rewardAddCoefficient = 2;
    }

    @Override
    public void takeEffect() {
        this.addToBot(new LoseHPAction(p, p, secondMagicNumber));
    }
}
