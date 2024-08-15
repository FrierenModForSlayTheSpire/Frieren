package FrierenMod.actions;

import FrierenMod.cards.AbstractBaseCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SynchroAction extends AbstractGameAction {
    private final AbstractCard card;

    public SynchroAction(AbstractCard card) {
        this.card = card;
    }

    @Override
    public void update() {
        if (!card.hasTag(AbstractBaseCard.Enum.ACCEL_SYNCHRO) && !card.hasTag(AbstractBaseCard.Enum.LIMIT_OVER_SYNCHRO) && !card.hasTag(AbstractBaseCard.Enum.SYNCHRO)) {
            this.isDone = true;
            return;
        }
        if (card.hasTag(AbstractBaseCard.Enum.ACCEL_SYNCHRO) && card.hasTag(AbstractBaseCard.Enum.LIMIT_OVER_SYNCHRO)) {
            this.addToBot(new AttackSpecialDamageRandomEnemyAction(15));
            this.addToBot(new DrawCardAction(2));
        } else if (card.hasTag(AbstractBaseCard.Enum.ACCEL_SYNCHRO)) {
            this.addToBot(new DrawCardAction(2));
        } else if (card.hasTag(AbstractBaseCard.Enum.LIMIT_OVER_SYNCHRO)) {
            this.addToBot(new AttackSpecialDamageRandomEnemyAction(15));
        } else if (card.hasTag(AbstractBaseCard.Enum.SYNCHRO)) {
            this.addToBot(new DrawCardAction(1));
        }
        this.triggerCardsInGroup(AbstractDungeon.player.drawPile);
        this.triggerCardsInGroup(AbstractDungeon.player.discardPile);
        this.isDone = true;
    }

    private void triggerCardsInGroup(CardGroup group) {
        for (AbstractCard c : group.group)
            if (c instanceof AbstractBaseCard) {
                ((AbstractBaseCard) c).afterSynchroFinished(card);
            }
    }
}
