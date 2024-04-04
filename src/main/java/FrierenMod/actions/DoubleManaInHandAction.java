package FrierenMod.actions;

import FrierenMod.gameHelpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class DoubleManaInHandAction extends AbstractGameAction {

    @Override
    public void update() {
        int hand = ChantHelper.getManaNumInHand();
        if(hand > 0){
            this.addToBot(new MakeManaInHandAction(hand));
        }
        this.isDone = true;
    }
}
