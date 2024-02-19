package FrierenMod.actions;

import FrierenMod.cards.AbstractFrierenCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class LoopAgainAction extends AbstractGameAction {
    private final int magicNumber;

    public LoopAgainAction(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    @Override
    public void update() {
        int exhaust = 0;
        for(AbstractCard c: AbstractDungeon.player.exhaustPile.group){
            if(c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isMagicPower)
                exhaust++;
        }
        if(exhaust > 0){
            this.addToBot(new MakeMagicPowerInDrawPileAction(exhaust/this.magicNumber));
        }
        this.isDone = true;
    }
}
