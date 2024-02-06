package FrierenMod.cardMods;

import FrierenMod.helpers.ModInfo;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class CostMod extends AbstractCardModifier {
    public static final String ID = ModInfo.makeID(CostMod.class.getSimpleName());

    private final int cost;

    public CostMod(int cost) {
        this.cost = cost;
    }

    public AbstractCardModifier makeCopy() {
        return new CostMod(this.cost);
    }

    public void onInitialApplication(AbstractCard card) {
        card.cost = this.cost;
        card.costForTurn = this.cost;
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}