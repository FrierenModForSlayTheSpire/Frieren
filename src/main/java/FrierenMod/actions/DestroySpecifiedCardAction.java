package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DestroySpecifiedCardAction extends AbstractGameAction {
    private final AbstractCard targetCard;
    private final CardGroup group;
    private final float startingDuration;
    private final boolean extraDraw;
    public DestroySpecifiedCardAction(AbstractCard targetCard, CardGroup group) {
        this.targetCard = targetCard;
        this.setValues(AbstractDungeon.player, AbstractDungeon.player, this.amount);
        this.group = group;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        this.extraDraw = false;
    }
    public DestroySpecifiedCardAction(AbstractCard targetCard, CardGroup group,boolean extraDraw) {
        this.targetCard = targetCard;
        this.setValues(AbstractDungeon.player, AbstractDungeon.player, this.amount);
        this.group = group;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        this.extraDraw = extraDraw;
    }
    public void update() {
        if (this.duration == this.startingDuration && this.group.contains(this.targetCard)) {
            this.resetCardBeforeMoving(this.targetCard);
            this.targetCard.exhaustOnUseOnce = false;
            this.targetCard.freeToPlayOnce = false;
            if(extraDraw && this.group.type == CardGroup.CardGroupType.HAND)
                this.addToBot(new DrawCardAction(1));
        }
        this.tickDuration();
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
