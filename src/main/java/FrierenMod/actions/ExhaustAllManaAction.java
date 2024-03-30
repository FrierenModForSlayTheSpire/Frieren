package FrierenMod.actions;

import FrierenMod.gameHelpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class ExhaustAllManaAction extends AbstractGameAction {
    private final int exhaustNumberInDrawPile;
    private final int exhaustNumberInHand;
    private final int exhaustNumberInDiscardPile;

    public ExhaustAllManaAction() {
        this.actionType = ActionType.WAIT;
        this.exhaustNumberInDrawPile = ChantHelper.getManaNumInDiscardPile();
        this.exhaustNumberInHand = ChantHelper.getManaNumInHand();
        this.exhaustNumberInDiscardPile = ChantHelper.getManaNumInDiscardPile();
    }

    public void update() {
        this.addToBot(new ExhaustManaInDrawPileAction(this.exhaustNumberInDrawPile));
        this.addToBot(new ExhaustManaInHandAction(this.exhaustNumberInHand));
        this.addToBot(new ExhaustManaInDiscardPileAction(this.exhaustNumberInDiscardPile));
        this.isDone = true;
    }
}
