package FrierenMod.actions;

import FrierenMod.cards.AbstractFrierenCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ExhaustManaInDrawPileAction extends AbstractGameAction {
    private final int exhaustNumber;

    public ExhaustManaInDrawPileAction(int exhaustNumber) {
        this.actionType = ActionType.WAIT;
        this.exhaustNumber = exhaustNumber;
    }

    public void update() {
        int counts = 0;
        for(AbstractCard c:AbstractDungeon.player.drawPile.group){
            if (counts >= this.exhaustNumber){
                break;
            }
            if (c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isMana) {
                counts++;
                this.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.drawPile));
            }
        }
        this.isDone = true;
    }
}
