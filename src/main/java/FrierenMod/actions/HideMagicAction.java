package FrierenMod.actions;

import FrierenMod.cards.AbstractBaseCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class HideMagicAction extends AbstractGameAction {
    @Override
    public void update() {
        for(AbstractCard c: AbstractDungeon.player.drawPile.group){
            if(c instanceof AbstractBaseCard && ((AbstractBaseCard) c).isMana){
                this.addToBot(new DrawPileToDiscardPileAction(c));
            }
        }
        for(AbstractCard c: AbstractDungeon.player.hand.group){
            if(c instanceof AbstractBaseCard && ((AbstractBaseCard) c).isMana){
                this.addToBot(new HandToDiscardPileAction(c));
            }
        }
        this.isDone = true;
    }
}
