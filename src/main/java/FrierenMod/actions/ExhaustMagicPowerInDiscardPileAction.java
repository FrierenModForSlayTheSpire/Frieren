package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

import static FrierenMod.tags.CustomTags.MAGIC_POWER;

public class ExhaustMagicPowerInDiscardPileAction extends AbstractGameAction {
    private final float startingDuration;
    private int exhaustNumber = 0;

    public ExhaustMagicPowerInDiscardPileAction(int exhaustNumber) {
        this.actionType = ActionType.WAIT;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        this.exhaustNumber = exhaustNumber;
    }

    public void update() {
        if (this.duration == this.startingDuration) {

            Iterator<AbstractCard> var1 = AbstractDungeon.player.discardPile.group.iterator();
            int counts = 0;
            while(var1.hasNext()) {
                AbstractCard c = (AbstractCard)var1.next();
                if (counts >= this.exhaustNumber){
                    break;
                }
                if (c.hasTag(MAGIC_POWER)) {
                    counts++;
                    this.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.discardPile));
                }
            }
            this.isDone = true;
        }
    }
}
