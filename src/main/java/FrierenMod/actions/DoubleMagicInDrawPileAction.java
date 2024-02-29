package FrierenMod.actions;

import FrierenMod.gameHelpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class DoubleMagicInDrawPileAction extends AbstractGameAction {
    @Override
    public void update() {
        int draw = ChantHelper.getMagicPowerNumInDrawPile();
        if(draw > 0){
            this.addToBot(new MakeManaInDrawPileAction(draw));
        }
        this.isDone = true;
    }
}
