package FrierenMod.actions;

import FrierenMod.helpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class ChantAllAction extends AbstractGameAction {
    @Override
    public void update() {
        ChantHelper helper = new ChantHelper();
        int hand = helper.getMagicPowerNumInHand();
        int draw = helper.getMagicPowerNumInDrawPile();
        int discard = helper.getMagicPowerNumInDiscardPile();
        this.addToBot(new ChantFromHandAction(hand));
        this.addToBot(new ChantFromDrawPileAction(draw));
        this.addToBot(new ChantFromDiscardPileAction(discard));
        this.isDone = true;
    }
}
