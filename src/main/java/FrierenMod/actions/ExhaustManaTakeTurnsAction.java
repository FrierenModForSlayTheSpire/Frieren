package FrierenMod.actions;

import FrierenMod.gameHelpers.CombatHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class ExhaustManaTakeTurnsAction extends AbstractGameAction {
    private final int requiredAmt;

    public ExhaustManaTakeTurnsAction(int requiredAmt) {
        this.requiredAmt = requiredAmt;
    }

    @Override
    public void update() {
        if(CombatHelper.getAllManaNum() >= requiredAmt){
            int draw = CombatHelper.getManaNumInDrawPile();
            int hand = CombatHelper.getManaNumInHand();
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
