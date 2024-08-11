package FrierenMod.cards.magicItems.factors;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.StrikeUpPower;

public class MeleeMage extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(MeleeMage.class.getSimpleName());

    public MeleeMage() {
        super(ID);
        loadRarity(MagicItemRarity.COMMON);
        this.rewardAddCoefficient = 1;
    }

    @Override
    public void takeEffect() {
        addToBot(new ApplyPowerAction(p, p, new StrikeUpPower(p, secondMagicNumber)));
    }
}
