package FrierenMod.actions;

import FrierenMod.cards.AbstractFrierenCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DrawChantAction extends AbstractGameAction {
    private int drawNumber = 0;

    public DrawChantAction(int drawNumber) {
        this.actionType = ActionType.WAIT;
        this.drawNumber = drawNumber;
    }

    public void update() {
        int counts = 0;
        for(AbstractCard c: AbstractDungeon.player.drawPile.group){
            if (counts >= this.drawNumber){
                break;
            }
            if(((AbstractFrierenCard)c).isChantCard){
                counts++;
                this.addToBot(new DrawPileToHandAction(c));
            }
        }
        this.isDone = true;
    }
}