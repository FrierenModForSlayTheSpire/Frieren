package FrierenMod.actions;

import FrierenMod.gameHelpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class ChantAllAction extends AbstractGameAction {
    private final boolean isChantUpgraded;
    private final AbstractCard c;
    public ChantAllAction(boolean isChantUpgraded, AbstractCard c){
        this.isChantUpgraded = isChantUpgraded;
        this.c = c;
    }
    @Override
    public void update() {
        int hand = ChantHelper.getMagicPowerNumInHand();
        int draw = ChantHelper.getMagicPowerNumInDrawPile();
        int discard = ChantHelper.getMagicPowerNumInDiscardPile();
        if(hand > 0){
            this.addToBot(new ChantFromHandAction(isChantUpgraded,hand));
        }
        if(draw > 0){
            c.block = c.baseBlock = draw;
            c.applyPowers();
            this.addToBot(new ChantFromDrawPileAction(isChantUpgraded,c.block,draw));
        }
        if(discard > 0){
            this.addToBot(new ChantFromDiscardPileAction(isChantUpgraded,discard));
        }
        this.isDone = true;
    }
}
