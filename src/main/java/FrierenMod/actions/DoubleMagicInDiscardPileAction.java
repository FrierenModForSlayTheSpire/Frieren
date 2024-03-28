package FrierenMod.actions;

import FrierenMod.gameHelpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class DoubleMagicInDiscardPileAction extends AbstractGameAction {
    @Override
    public void update() {
        int discard = ChantHelper.getManaNumInDiscardPile();
        if(discard > 0){
            this.addToBot(new MakeManaInDiscardAction(discard));
        }
        this.isDone = true;
    }
}
