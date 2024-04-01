package FrierenMod.actions;

import FrierenMod.gameHelpers.CombatHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class DoubleManaInHandAction extends AbstractGameAction {

    @Override
    public void update() {
        int hand = CombatHelper.getManaNumInHand();
        if(hand > 0){
            this.addToBot(new MakeManaInHandAction(hand));
        }
        this.isDone = true;
    }
}
