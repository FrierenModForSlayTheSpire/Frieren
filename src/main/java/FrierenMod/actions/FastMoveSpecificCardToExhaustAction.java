package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FastMoveSpecificCardToExhaustAction extends AbstractGameAction {
    private final AbstractCard targetCard;
    private final CardGroup group;
    private final float startingDuration;

    public FastMoveSpecificCardToExhaustAction(AbstractCard targetCard, CardGroup group) {
        this.targetCard = targetCard;
        this.setValues(AbstractDungeon.player, AbstractDungeon.player, this.amount);
        this.group = group;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }
    public void update() {
        if (this.duration == this.startingDuration && this.group.contains(this.targetCard)) {
            this.moveToExhaustPile(this.targetCard);
            this.targetCard.exhaustOnUseOnce = false;
            this.targetCard.freeToPlayOnce = false;
        }
        this.tickDuration();
    }
    public void moveToExhaustPile(AbstractCard c) {
        this.resetCardBeforeMoving(c);
        AbstractDungeon.player.exhaustPile.addToTop(c);
    }
    private void resetCardBeforeMoving(AbstractCard c) {
        if (AbstractDungeon.player.hoveredCard == c) {
            AbstractDungeon.player.releaseCard();
        }
        AbstractDungeon.actionManager.removeFromQueue(c);
        c.unhover();
        c.untip();
        c.stopGlowing();
        this.group.group.remove(c);
    }
}
