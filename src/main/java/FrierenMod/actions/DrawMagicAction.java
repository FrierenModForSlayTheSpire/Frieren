package FrierenMod.actions;

import FrierenMod.cards.AbstractMagicianCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DrawMagicAction extends AbstractGameAction {
    private int drawNumber = 0;

    public DrawMagicAction(int drawNumber) {
        this.drawNumber = drawNumber;
    }

    public void update() {
        int counts = 0;
        for(AbstractCard c:AbstractDungeon.player.drawPile.group){
            if (counts >= this.drawNumber){
                break;
            }
            if (c instanceof AbstractMagicianCard && ((AbstractMagicianCard) c).isMana) {
                counts++;
                this.addToTop(new DrawPileToHandAction(c));
            }
        }
        this.isDone = true;
    }
}
