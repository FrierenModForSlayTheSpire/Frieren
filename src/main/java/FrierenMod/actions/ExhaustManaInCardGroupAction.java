package FrierenMod.actions;

import FrierenMod.cards.AbstractBaseCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ExhaustManaInCardGroupAction extends AbstractGameAction {
    private final int exhaustNumber;
    private final int pileNumber;

    public ExhaustManaInCardGroupAction(int exhaustNumber, int pileNumber) {
        this.pileNumber = pileNumber;
        this.actionType = ActionType.WAIT;
        this.exhaustNumber = exhaustNumber;
    }

    public void update() {
        int counts = 0;
        switch (pileNumber) {
            case 0:
                for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                    if (counts >= this.exhaustNumber) {
                        break;
                    }
                    if (c.hasTag(AbstractBaseCard.Enum.MANA)) {
                        counts++;
                        this.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.drawPile, true));
                    }
                }
                break;
            case 1:
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (counts >= this.exhaustNumber) {
                        break;
                    }
                    if (c.hasTag(AbstractBaseCard.Enum.MANA)) {
                        counts++;
                        this.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand, true));
                    }
                }
                break;
            case 2:
                for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                    if (counts >= this.exhaustNumber) {
                        break;
                    }
                    if (c.hasTag(AbstractBaseCard.Enum.MANA)) {
                        counts++;
                        this.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.discardPile, true));
                    }
                }
                break;
        }
        this.isDone = true;
    }
}
