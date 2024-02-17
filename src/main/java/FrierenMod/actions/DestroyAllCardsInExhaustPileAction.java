package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DestroyAllCardsInExhaustPileAction extends AbstractGameAction {
    public void update() {
        if (this.duration == this.startDuration) {
            AbstractDungeon.player.exhaustPile.clear();
            this.isDone = true;
        }
    }
}
