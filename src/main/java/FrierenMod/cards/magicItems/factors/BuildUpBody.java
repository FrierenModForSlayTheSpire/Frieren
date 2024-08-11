package FrierenMod.cards.magicItems.factors;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class BuildUpBody extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BuildUpBody.class.getSimpleName());

    public BuildUpBody() {
        super(ID);
        loadRarity(MagicItemRarity.COMMON);
    }

    @Override
    public void takeEffect() {
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.secondMagicNumber)));
    }
}
