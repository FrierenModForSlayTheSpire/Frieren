package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DrawCardByTagAction extends AbstractGameAction {
    private final int drawNumber;
    private final AbstractCard.CardTags tag;

    public DrawCardByTagAction(int drawNumber, AbstractCard.CardTags tag) {
        this.tag = tag;
        this.actionType = ActionType.WAIT;
        this.drawNumber = drawNumber;
    }

    public void update() {
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (counts >= this.drawNumber) {
                break;
            }
            if (c.hasTag(tag)) {
                counts++;
                this.addToBot(new DrawPileToHandAction(c));
            }
        }
        this.isDone = true;
    }
}