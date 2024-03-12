package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DestroyAllCardsInDiscardPileAction extends AbstractGameAction {
    public void update() {
        if (this.duration == this.startDuration) {
            CardGroup discardPile = AbstractDungeon.player.discardPile;
            for (int i = 0; i < discardPile.size(); i++) {
                AbstractCard card = discardPile.group.get(i);
                this.addToTop(new DestroySpecifiedCardAction(card,discardPile));
            }
            this.isDone = true;
        }
    }
}
