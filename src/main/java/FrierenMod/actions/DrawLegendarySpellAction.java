package FrierenMod.actions;

import FrierenMod.cards.AbstractBaseCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DrawLegendarySpellAction extends AbstractGameAction {
    private final int drawNumber;

    public DrawLegendarySpellAction(int drawNumber) {
        this.actionType = ActionType.WAIT;
        this.drawNumber = drawNumber;
    }

    public void update() {
        int counts = 0;
        for(AbstractCard c: AbstractDungeon.player.drawPile.group){
            if (counts >= this.drawNumber){
                break;
            }
            if(c.hasTag(AbstractBaseCard.Enum.LEGENDARY_SPELL)){
                counts++;
                this.addToBot(new DrawPileToHandAction(c));
            }
        }
        this.isDone = true;
    }
}