package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import static FrierenMod.gameHelpers.ChantHelper.*;

public class ExhaustManaTakeTurnsAction extends AbstractGameAction {
    private final int requiredAmt;

    public ExhaustManaTakeTurnsAction(int requiredAmt) {
        this.requiredAmt = requiredAmt;
    }

    @Override
    public void update() {
        if(getAllManaNum() >= requiredAmt){
            int draw = getManaNumInDrawPile();
            int hand = getManaNumInHand();
            if(draw >= requiredAmt){
                this.addToBot(new ExhaustManaInDrawPileAction(requiredAmt));
            }
            else {
                this.addToBot(new ExhaustManaInDrawPileAction(draw));
                int diff = requiredAmt - draw;
                if(diff <= hand){
                    this.addToBot(new ExhaustManaInHandAction(diff));
                }else {
                    this.addToBot(new ExhaustManaInHandAction(hand));
                    int diff2 = requiredAmt - hand - draw;
                    this.addToBot(new ExhaustManaInDiscardPileAction(diff2));
                }
            }
        }
        this.isDone = true;
    }
}
