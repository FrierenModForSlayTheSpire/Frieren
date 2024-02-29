package FrierenMod.actions;

import FrierenMod.gameHelpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class DoubleMagicInHandAction extends AbstractGameAction {

    @Override
    public void update() {
        int hand = ChantHelper.getMagicPowerNumInHand();
        if(hand > 0){
            this.addToBot(new MakeManaInHandAction(hand));
        }
        this.isDone = true;
    }
}
