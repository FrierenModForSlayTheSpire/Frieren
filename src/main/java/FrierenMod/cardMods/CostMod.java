package FrierenMod.cardMods;

import FrierenMod.cards.tempCards.FangYuMoFa2;
import FrierenMod.helpers.ModHelper;
import FrierenMod.modcore.FrierenMod;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class CostMod extends AbstractCardModifier {
    public static final String ID = ModHelper.makePath(CostMod.class.getSimpleName());

    private int cost;

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