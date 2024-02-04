package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DrawPileToDiscardPileAction extends AbstractGameAction {
    private final AbstractCard card;

    public DrawPileToDiscardPileAction(AbstractCard card) {
        this.card = card;
    }

    public void update() {
        if (AbstractDungeon.player.drawPile.contains(this.card)) {
            AbstractDungeon.player.discardPile.addToRandomSpot(this.card);
            this.card.unhover();
            this.card.setAngle(0.0F, true);
            this.card.lighten(false);
            this.card.drawScale = 0.12F;
            this.card.targetDrawScale = 0.75F;
            this.card.applyPowers();
            AbstractDungeon.player.drawPile.removeCard(this.card);
        }
        this.tickDuration();
        this.isDone = true;
    }
}
