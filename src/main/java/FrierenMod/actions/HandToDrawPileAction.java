package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class HandToDrawPileAction extends AbstractGameAction {
    private final AbstractCard card;

    public HandToDrawPileAction(AbstractCard card) {
        this.card = card;
    }

    public void update() {
        if (AbstractDungeon.player.hand.contains(this.card)) {
            AbstractDungeon.player.drawPile.addToRandomSpot(this.card);
            this.card.stopGlowing();
            this.card.unhover();
            this.card.setAngle(0.0F, true);
            this.card.lighten(false);
            this.card.drawScale = 0.12F;
            this.card.targetDrawScale = 0.75F;
            AbstractDungeon.player.hand.removeCard(this.card);
        }

        AbstractDungeon.player.hand.refreshHandLayout();
        AbstractDungeon.player.hand.glowCheck();
        this.tickDuration();
        this.isDone = true;
    }
}
