package FrierenMod.actions;

import FrierenMod.helpers.ChantHelper;
import FrierenMod.helpers.LegendMagicHelper;
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
        AbstractCard c1 = new ChantHelper().getRandomCard();
        AbstractCard c2 = new LegendMagicHelper().getRandomCard();
        if(this.upgraded){
            c1.setCostForTurn(0);
            c2.setCostForTurn(0);
        }
        this.addToBot(new MakeTempCardInHandAction(c1));
        this.addToBot(new MakeTempCardInHandAction(c2));
        this.isDone = true;
    }
}
