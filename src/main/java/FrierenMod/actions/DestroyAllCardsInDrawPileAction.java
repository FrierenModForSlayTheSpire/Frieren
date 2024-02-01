package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DestroyAllCardsInDrawPileAction extends AbstractGameAction {
    public void update() {
        if (this.duration == this.startDuration) {
            CardGroup drawPile = AbstractDungeon.player.drawPile;
            for (int i = 0; i < drawPile.size(); i++) {
                AbstractCard card = drawPile.group.get(i);
                addToTop((AbstractGameAction) new ExhaustSpecificCardAction(card, drawPile));
            }
            this.isDone = true;
        }
    }
}