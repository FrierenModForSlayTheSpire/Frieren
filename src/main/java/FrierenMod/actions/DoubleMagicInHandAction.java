package FrierenMod.actions;

import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.helpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;

public class DoubleMagicInHandAction extends AbstractGameAction {
    @Override
    public void update() {
        int hand = new ChantHelper().getMagicPowerNumInHand();
        if(hand > 0){
            this.addToBot(new MakeTempCardInHandAction(new MagicPower(),hand));
        }
        this.isDone = true;
    }
}
