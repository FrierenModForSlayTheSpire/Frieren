package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

import static FrierenMod.tags.CustomTags.MAGIC_POWER;

public class DrawMagicFromDiscardPileAction extends AbstractGameAction {
    private int drawNumber = 0;

    public DrawMagicFromDiscardPileAction(int drawNumber) {
        this.actionType = ActionType.WAIT;
        this.drawNumber = drawNumber;
    }

    public void update() {
        Iterator<AbstractCard> var1 = AbstractDungeon.player.discardPile.group.iterator();
        int counts = 0;
        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            if (counts >= this.drawNumber){
                break;
            }
            if (c.tags.contains(MAGIC_POWER)) {
                counts++;
                this.addToTop(new DiscardPileToHandAction(c));
            }
        }
        this.isDone = true;
    }
}
