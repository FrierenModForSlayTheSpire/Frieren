package FrierenMod.actions;

import FrierenMod.cards.canAutoAdd.white.LittleFire;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class LittleFireAction extends AbstractGameAction {
    private final AbstractCard card;

    public LittleFireAction(AbstractCard card, int amount) {
        this.card = card;
        this.amount = amount;
    }

    public void update() {
        this.card.baseDamage += this.amount;
        this.card.applyPowers();
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c instanceof LittleFire) {
                c.baseDamage += this.amount;
                c.applyPowers();
            }
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c instanceof LittleFire) {
                c.baseDamage += this.amount;
                c.applyPowers();
            }
        }
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof LittleFire) {
                c.baseDamage += this.amount;
                c.applyPowers();
            }
        }
        this.isDone = true;
    }
}
