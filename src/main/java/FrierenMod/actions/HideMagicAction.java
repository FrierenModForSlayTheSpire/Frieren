package FrierenMod.actions;

import FrierenMod.cards.AbstractFrierenCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class HideMagicAction extends AbstractGameAction {
    @Override
    public void update() {
        for(AbstractCard c: AbstractDungeon.player.drawPile.group){
            if(c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isMagicPower){
                this.addToBot(new DrawPileToDiscardPileAction(c));
            }
        }
        for(AbstractCard c: AbstractDungeon.player.hand.group){
            if(c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isMagicPower){
                this.addToBot(new HandToDiscardPileAction(c));
            }
        }
        this.isDone = true;
    }
}
