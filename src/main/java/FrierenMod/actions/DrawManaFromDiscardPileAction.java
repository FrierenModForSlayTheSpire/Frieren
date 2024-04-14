package FrierenMod.actions;

import FrierenMod.cards.AbstractBaseCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DrawManaFromDiscardPileAction extends AbstractGameAction {
    private int drawNumber = 0;

    public DrawManaFromDiscardPileAction(int drawNumber) {
        this.actionType = ActionType.WAIT;
        this.drawNumber = drawNumber;
    }

    public void update() {
        int counts = 0;
        for(AbstractCard c:AbstractDungeon.player.discardPile.group){
            if(c instanceof AbstractBaseCard && ((AbstractBaseCard) c).isMana){
                counts++;
                this.addToTop(new DiscardPileToHandAction(c));
            }
            if(counts >= this.drawNumber)
                break;
        }
        this.isDone = true;
    }
}
