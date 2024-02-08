package FrierenMod.actions;

import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.helpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;

public class DoubleMagicInDrawPileAction extends AbstractGameAction {
    @Override
    public void update() {
        int draw = new ChantHelper().getMagicPowerNumInDrawPile();
        if(draw > 0){
            this.addToBot(new MakeTempCardInDrawPileAction(new MagicPower(),draw,true,true));
        }
        this.isDone = true;
    }
}
