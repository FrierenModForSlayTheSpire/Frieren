package FrierenMod.actions;

import FrierenMod.gameHelpers.ChantHelper;
import FrierenMod.gameHelpers.LegendarySpellHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class MagicBookAction extends AbstractGameAction {
    private final boolean upgraded;

    public MagicBookAction(boolean upgraded) {
        this.upgraded = upgraded;
    }

    @Override
    public void update() {
        AbstractCard c1 = ChantHelper.getRandomCard();
        AbstractCard c2 = LegendarySpellHelper.getRandomCard();
        if(this.upgraded){
            c1.setCostForTurn(c1.cost-2);
            c2.setCostForTurn(c2.cost-2);
        }
        this.addToBot(new MakeTempCardInHandAction(c1));
        this.addToBot(new MakeTempCardInHandAction(c2));
        this.isDone = true;
    }
}
