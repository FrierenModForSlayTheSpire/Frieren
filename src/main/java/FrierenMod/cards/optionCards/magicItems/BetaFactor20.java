package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;

public class BetaFactor20 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor20.class.getSimpleName());

    public BetaFactor20() {
        super(ID);
        loadRarity(MagicItemRarity.COMMON);
    }

    @Override
    public void takeEffect() {
        addToBot(new ApplyPowerAction(this.p, this.p, new DrawCardNextTurnPower(this.p, secondMagicNumber), secondMagicNumber));
    }
}
