package FrierenMod.actions;

import FrierenMod.cards.AbstractBaseCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.BetterDrawPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PancakeAction extends AbstractGameAction {
    private final int magicNumber;

    public PancakeAction(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    @Override
    public void update() {
        for(AbstractCard c:AbstractDungeon.player.hand.group){
            if(c instanceof AbstractBaseCard && ((AbstractBaseCard) c).isMana){
                this.addToBot(new HandToDrawPileAction(c));
                break;
            }
        }
        this.addToBot(new BetterDrawPileToHandAction(this.magicNumber));
        this.isDone = true;
    }
}
