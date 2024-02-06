package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DestroyAllCardsInHandAction extends AbstractGameAction{
    public void update() {
        if (this.duration == this.startDuration) {
            CardGroup hand = AbstractDungeon.player.hand;
            for (int i = 0; i < hand.size(); i++) {
                AbstractCard card = hand.group.get(i);
                addToTop((AbstractGameAction) new ExhaustSpecificCardAction(card, hand));
            }
            this.isDone = true;
        }
    }
}
