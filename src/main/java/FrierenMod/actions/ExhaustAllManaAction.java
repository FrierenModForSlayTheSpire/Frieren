package FrierenMod.actions;

import FrierenMod.gameHelpers.CombatHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class ExhaustAllManaAction extends AbstractGameAction {
    private final int exhaustNumberInDrawPile;
    private final int exhaustNumberInHand;
    private final int exhaustNumberInDiscardPile;

    public ExhaustAllManaAction() {
        this.actionType = ActionType.WAIT;
        this.exhaustNumberInDrawPile = CombatHelper.getManaNumInDrawPile();
        this.exhaustNumberInHand = CombatHelper.getManaNumInHand();
        this.exhaustNumberInDiscardPile = CombatHelper.getManaNumInDiscardPile();
    }

    public void update() {
        this.addToBot(new ExhaustManaInCardGroupAction(this.exhaustNumberInDrawPile,0));
        this.addToBot(new ExhaustManaInCardGroupAction(this.exhaustNumberInHand,1));
        this.addToBot(new ExhaustManaInCardGroupAction(this.exhaustNumberInDiscardPile,2));
        this.isDone = true;
    }
}
