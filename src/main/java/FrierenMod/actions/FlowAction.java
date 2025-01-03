package FrierenMod.actions;

import FrierenMod.cards.AbstractBaseCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FlowAction extends AbstractGameAction {
    private final int magicNumber;

    public FlowAction(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    @Override
    public void update() {
        if (!AbstractDungeon.player.drawPile.isEmpty()) {
            int counts = 0;
            for(AbstractCard c : AbstractDungeon.player.drawPile.group){
                if(counts >= this.magicNumber){
                    break;
                }
                if(c.hasTag(AbstractBaseCard.Enum.MANA)){
                    counts++;
                    this.addToBot(new DrawPileToDiscardPileAction(c));
                }
            }
        }
        this.isDone =true;
    }
}
