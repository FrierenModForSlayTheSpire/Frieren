package FrierenMod.actions;

import FrierenMod.cards.AbstractBaseCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DrawManaAction extends AbstractGameAction {
    private final int drawNumber;

    public DrawManaAction(int drawNumber) {
        this.drawNumber = drawNumber;
    }

    public void update() {
        int counts = 0;
        for(AbstractCard c:AbstractDungeon.player.drawPile.group){
            if (counts >= this.drawNumber){
                break;
            }
            if (c instanceof AbstractBaseCard && ((AbstractBaseCard) c).isMana) {
                counts++;
                this.addToTop(new DrawPileToHandAction(c));
            }
        }
        this.isDone = true;
    }
}
