package FrierenMod.cards.magicItems.factors;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;

public class Sleeeep extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(Sleeeep.class.getSimpleName());

    public Sleeeep() {
        super(ID);
        loadRarity(MagicItemRarity.COMMON);
    }

    @Override
    public void takeEffect() {
        addToBot(new ApplyPowerAction(this.p, this.p, new DrawCardNextTurnPower(this.p, secondMagicNumber), secondMagicNumber));
    }
}
