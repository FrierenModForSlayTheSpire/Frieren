package FrierenMod.actions;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.tempCards.MagicPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.BetterDrawPileToHandAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PancakeAction extends AbstractGameAction {
    private final int magicNumber;

    public PancakeAction(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    @Override
    public void update() {
        for(AbstractCard c:AbstractDungeon.player.hand.group){
            if(c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isMagicPower){
                this.addToBot(new HandToDrawPileAction(c));
                break;
            }
        }
        this.addToBot(new BetterDrawPileToHandAction(this.magicNumber));
        this.isDone = true;
    }
}
