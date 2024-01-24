package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

import static FrierenMod.tags.CustomTags.CHANT;
import static FrierenMod.tags.CustomTags.MAGIC_POWER;

public class DrawChantAction extends AbstractGameAction {
    private final float startingDuration;
    private int drawNumber = 0;

    public DrawChantAction(int drawNumber) {
        this.actionType = ActionType.WAIT;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        this.drawNumber = drawNumber;
    }

    public void update() {
        if (this.duration == this.startingDuration) {
            Iterator<AbstractCard> var1 = AbstractDungeon.player.drawPile.group.iterator();
            int counts = 0;
            while(var1.hasNext()) {
                AbstractCard c = (AbstractCard)var1.next();
                if (counts >= this.drawNumber){
                    break;
                }
                if (c.tags.contains(CHANT)) {
                    counts++;
                    this.addToTop(new DrawPileToHandAction(c));
                }
            }
            this.isDone = true;
        }
    }
}