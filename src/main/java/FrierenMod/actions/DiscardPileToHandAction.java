package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DiscardPileToHandAction extends AbstractGameAction {
    private final AbstractCard card;

    public DiscardPileToHandAction(AbstractCard card) {
        this.card = card;
    }

    public void update() {
        if (AbstractDungeon.player.discardPile.contains(this.card) && AbstractDungeon.player.hand.group.size() < 10) {
            AbstractDungeon.player.hand.addToHand(this.card);
            this.card.unhover();
            this.card.setAngle(0.0F, true);
            this.card.lighten(false);
            this.card.drawScale = 0.12F;
            this.card.targetDrawScale = 0.75F;
            this.card.applyPowers();
            AbstractDungeon.player.discardPile.removeCard(this.card);
        }
        AbstractDungeon.player.hand.refreshHandLayout();
        AbstractDungeon.player.hand.glowCheck();
        this.tickDuration();
        this.isDone = true;
    }
}
