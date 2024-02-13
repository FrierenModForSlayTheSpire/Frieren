package FrierenMod.actions;

import FrierenMod.gameHelpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class ExhaustMagicPowerTakeTurnsAction extends AbstractGameAction {
    private final int requiredAmt;

    public ExhaustMagicPowerTakeTurnsAction(int requiredAmt) {
        this.requiredAmt = requiredAmt;
    }

    @Override
    public void update() {
        ChantHelper helper = new ChantHelper();
        if(helper.getAllMagicPowerNum() >= requiredAmt){
            int draw = helper.getMagicPowerNumInDrawPile();
            int hand = helper.getMagicPowerNumInHand();
            int discard = helper.getMagicPowerNumInDiscardPile();
            if(draw >= requiredAmt){
                this.addToBot(new ExhaustMagicPowerInDrawPileAction(requiredAmt));
            }
            else {
                this.addToBot(new ExhaustMagicPowerInDrawPileAction(draw));
                int diff = requiredAmt - draw;
                if(diff <= hand){
                    this.addToBot(new ExhaustMagicPowerInHandAction(diff));
                }else {
                    this.addToBot(new ExhaustMagicPowerInHandAction(hand));
                    int diff2 = requiredAmt - hand - draw;
                    this.addToBot(new ExhaustMagicPowerInDiscardPileAction(diff2));
                }
            }
        }
        this.isDone = true;
    }
}
