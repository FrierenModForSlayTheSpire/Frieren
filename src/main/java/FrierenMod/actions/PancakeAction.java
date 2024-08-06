package FrierenMod.actions;

import FrierenMod.cards.AbstractBaseCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.BetterDrawPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PancakeAction extends AbstractGameAction {
    private int magicNumber;

    public PancakeAction(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    @Override
    public void update() {
        int count = 0;
        for(AbstractCard c:AbstractDungeon.player.hand.group){
            if(count >= magicNumber)
                break;
            if(c instanceof AbstractBaseCard && ((AbstractBaseCard) c).isMana){
                this.addToBot(new HandToDrawPileAction(c));
                count++;
            }
        }
        if(count >= magicNumber)
            this.addToBot(new BetterDrawPileToHandAction(1));
        this.isDone = true;
    }
}
