package FrierenMod.actions;

import FrierenMod.cards.AbstractMagicianCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ExhaustManaInHandAction extends AbstractGameAction {
    private final int exhaustNumber;

    public ExhaustManaInHandAction(int exhaustNumber) {
        this.actionType = ActionType.WAIT;
        this.exhaustNumber = exhaustNumber;
    }

    public void update() {
        int counts = 0;
        for(AbstractCard c:AbstractDungeon.player.hand.group){
            if (counts >= this.exhaustNumber){
                break;
            }
            if (c instanceof AbstractMagicianCard && ((AbstractMagicianCard) c).isMana) {
                counts++;
                this.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
            }
        }
        this.isDone = true;
    }
}
