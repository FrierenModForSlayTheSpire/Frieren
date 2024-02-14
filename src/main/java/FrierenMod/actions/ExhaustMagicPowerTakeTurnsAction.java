package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import static FrierenMod.gameHelpers.ChantHelper.*;

public class ExhaustMagicPowerTakeTurnsAction extends AbstractGameAction {
    private final int requiredAmt;

    public ExhaustMagicPowerTakeTurnsAction(int requiredAmt) {
        this.requiredAmt = requiredAmt;
    }

    @Override
    public void update() {
        if(getAllMagicPowerNum() >= requiredAmt){
            int draw = getMagicPowerNumInDrawPile();
            int hand = getMagicPowerNumInHand();
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
