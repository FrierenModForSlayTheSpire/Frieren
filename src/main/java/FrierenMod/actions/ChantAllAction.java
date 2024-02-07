package FrierenMod.actions;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.helpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class ChantAllAction extends AbstractGameAction {
    private final AbstractCard c;
    public ChantAllAction(AbstractCard c){
        this.c = c;
    }
    @Override
    public void update() {
        ChantHelper helper = new ChantHelper();
        int hand = helper.getMagicPowerNumInHand();
        int draw = helper.getMagicPowerNumInDrawPile();
        int discard = helper.getMagicPowerNumInDiscardPile();
        if(hand > 0){
            this.addToBot(new ChantFromHandAction(hand));
        }
        if(draw > 0){
            c.block = c.baseBlock = draw;
            c.applyPowers();
            this.addToBot(new ChantFromDrawPileAction(c.block,draw));
        }
        if(discard > 0){
            this.addToBot(new ChantFromDiscardPileAction(discard));
        }
        this.isDone = true;
    }
}
