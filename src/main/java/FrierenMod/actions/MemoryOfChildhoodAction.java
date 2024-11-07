package FrierenMod.actions;

import FrierenMod.cards.tempCards.SpecializedOffensiveMagic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class MemoryOfChildhoodAction extends AbstractGameAction {
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            ArrayList<AbstractCard> playCards = new ArrayList<>();
            for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
                if (c.cardID.equals(SpecializedOffensiveMagic.ID))
                    playCards.add(c.makeStatEquivalentCopy());
            }
            for (AbstractCard c : playCards) {
                c.freeToPlayOnce = true;
                switch (c.target) {
                    case SELF_AND_ENEMY:
                    case ENEMY:
                        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c,
                                AbstractDungeon.getRandomMonster()));
                        continue;
                }
                AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, null));
            }
        }
        tickDuration();
    }
}
