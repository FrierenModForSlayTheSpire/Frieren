package FrierenMod.actions;

import FrierenMod.gameHelpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class ExhaustAllMagicPowerAction extends AbstractGameAction {
    private final int exhaustNumberInDrawPile;
    private final int exhaustNumberInHand;
    private final int exhaustNumberInDiscardPile;

    public ExhaustAllMagicPowerAction() {
        this.actionType = ActionType.WAIT;
        ChantHelper helper = new ChantHelper();
        this.exhaustNumberInDrawPile = helper.getMagicPowerNumInDiscardPile();
        this.exhaustNumberInHand = helper.getMagicPowerNumInHand();
        this.exhaustNumberInDiscardPile = helper.getMagicPowerNumInDiscardPile();
    }

    public void update() {
        this.addToBot(new ExhaustMagicPowerInDrawPileAction(this.exhaustNumberInDrawPile));
        this.addToBot(new ExhaustMagicPowerInHandAction(this.exhaustNumberInHand));
        this.addToBot(new ExhaustMagicPowerInDiscardPileAction(this.exhaustNumberInDiscardPile));
        this.isDone = true;
    }
}
