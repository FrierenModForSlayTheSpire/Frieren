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
                this.addToBot(new ExhaustManaInCardGroupAction(requiredAmt,0));
            }
            else {
                this.addToBot(new ExhaustManaInCardGroupAction(draw,0));
                int diff = requiredAmt - draw;
                if(diff <= hand){
                    this.addToBot(new ExhaustManaInCardGroupAction(diff,1));
                }else {
                    this.addToBot(new ExhaustManaInCardGroupAction(hand,1));
                    int diff2 = requiredAmt - hand - draw;
                    this.addToBot(new ExhaustManaInCardGroupAction(diff2,2));
                }
            }
        }
        this.isDone = true;
    }
}
