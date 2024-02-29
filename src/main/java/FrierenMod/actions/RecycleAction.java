package FrierenMod.actions;

import FrierenMod.cards.AbstractFrierenCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RecycleAction extends AbstractGameAction {
    private final int magicNumber;

    public RecycleAction(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    @Override
    public void update() {
        int exhaust = 0;
        for(AbstractCard c: AbstractDungeon.player.discardPile.group){
            if(c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isMana)
                exhaust++;
        }
        if(exhaust > 0){
            this.addToBot(new MakeManaInDrawPileAction(exhaust/this.magicNumber));
        }
        this.isDone = true;
    }
}
