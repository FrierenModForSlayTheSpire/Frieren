package FrierenMod.actions;

import FrierenMod.gameHelpers.CombatHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class ExhaustAllManaAction extends AbstractGameAction {
    private final int exhaustNumberInDrawPile;
    private final int exhaustNumberInHand;
    private final int exhaustNumberInDiscardPile;

    public ExhaustAllManaAction() {
        this.actionType = ActionType.WAIT;
        this.exhaustNumberInDrawPile = CombatHelper.getManaNumInDiscardPile();
        this.exhaustNumberInHand = CombatHelper.getManaNumInHand();
        this.exhaustNumberInDiscardPile = CombatHelper.getManaNumInDiscardPile();
    }

    public void update() {
        this.addToBot(new ExhaustManaInDrawPileAction(this.exhaustNumberInDrawPile));
        this.addToBot(new ExhaustManaInHandAction(this.exhaustNumberInHand));
        this.addToBot(new ExhaustManaInDiscardPileAction(this.exhaustNumberInDiscardPile));
        this.isDone = true;
    }
}
