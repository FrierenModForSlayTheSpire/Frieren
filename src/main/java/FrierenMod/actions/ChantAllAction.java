package FrierenMod.actions;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.gameHelpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ChantAllAction extends AbstractGameAction {
    private final AbstractCard c;
    public ChantAllAction(AbstractCard c){
        this.c = c;
    }
    @Override
    public void update() {
        int hand = ChantHelper.getMagicPowerNumInHand();
        int draw = ChantHelper.getMagicPowerNumInDrawPile();
        int discard = ChantHelper.getMagicPowerNumInDiscardPile();
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
        if(hand > 0 || draw > 0 || discard > 0){
            for(AbstractCard c: AbstractDungeon.player.discardPile.group){
                if(c instanceof AbstractFrierenCard)
                    ((AbstractFrierenCard) c).triggerExhaustedCardsOnChant();
            }
        }
        this.isDone = true;
    }
}
