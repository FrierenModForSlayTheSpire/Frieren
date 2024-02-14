package FrierenMod.actions;

import FrierenMod.gameHelpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class DoubleMagicInHandAction extends AbstractGameAction {
    private final boolean canCanMagic;

    public DoubleMagicInHandAction(boolean canCanMagic) {
        this.canCanMagic = canCanMagic;
    }

    @Override
    public void update() {
        int hand = ChantHelper.getMagicPowerNumInHand();
        if(hand > 0){
            this.addToBot(new MakeMagicPowerInHandAction(hand,canCanMagic));
        }
        this.isDone = true;
    }
}
