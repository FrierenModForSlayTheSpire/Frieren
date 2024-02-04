package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

import static FrierenMod.tags.CustomTags.MAGIC_POWER;

public class ExhaustMagicPowerInHandAction extends AbstractGameAction {
    private final int exhaustNumber;

    public ExhaustMagicPowerInHandAction(int exhaustNumber) {
        this.actionType = ActionType.WAIT;
        this.exhaustNumber = exhaustNumber;
    }

    public void update() {
        Iterator<AbstractCard> var1 = AbstractDungeon.player.hand.group.iterator();
        int counts = 0;
        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            if (counts >= this.exhaustNumber){
                break;
            }
            if (c.hasTag(MAGIC_POWER)) {
                counts++;
                this.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
            }
        }
        this.isDone = true;
    }
}
