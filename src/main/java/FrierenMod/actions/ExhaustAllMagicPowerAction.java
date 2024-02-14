package FrierenMod.actions;

import FrierenMod.gameHelpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class ExhaustAllMagicPowerAction extends AbstractGameAction {
    private final int exhaustNumberInDrawPile;
    private final int exhaustNumberInHand;
    private final int exhaustNumberInDiscardPile;

    public ExhaustAllMagicPowerAction() {
        this.actionType = ActionType.WAIT;
        this.exhaustNumberInDrawPile = ChantHelper.getMagicPowerNumInDiscardPile();
        this.exhaustNumberInHand = ChantHelper.getMagicPowerNumInHand();
        this.exhaustNumberInDiscardPile = ChantHelper.getMagicPowerNumInDiscardPile();
    }

    public void update() {
        this.addToBot(new ExhaustMagicPowerInDrawPileAction(this.exhaustNumberInDrawPile));
        this.addToBot(new ExhaustMagicPowerInHandAction(this.exhaustNumberInHand));
        this.addToBot(new ExhaustMagicPowerInDiscardPileAction(this.exhaustNumberInDiscardPile));
        this.isDone = true;
    }
}
