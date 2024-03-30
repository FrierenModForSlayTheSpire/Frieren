package FrierenMod.actions;

import FrierenMod.gameHelpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class FinalChantAction extends AbstractGameAction {
    private final AbstractCard c;
    public FinalChantAction(AbstractCard c){
        this.c = c;
    }
    @Override
    public void update() {
        boolean haveNotTriggered = true;
        int hand = ChantHelper.getManaNumInHand();
        int draw = ChantHelper.getManaNumInDrawPile();
        int discard = ChantHelper.getManaNumInDiscardPile();
        if(hand > 0){
            this.addToBot(new ChantFromHandAction(hand,haveNotTriggered));
            haveNotTriggered = false;
        }
        if(draw > 0){
            c.block = c.baseBlock = draw;
            c.applyPowers();
            this.addToBot(new ChantFromDrawPileAction(c.block,draw,haveNotTriggered));
            haveNotTriggered = false;
        }
        if(discard > 0){
            this.addToBot(new ChantFromDiscardPileAction(discard,haveNotTriggered));
        }
        this.isDone = true;
    }
}
