package FrierenMod.actions;

import FrierenMod.gameHelpers.Status;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class ReceiveEverythingAction extends AbstractGameAction {
    private final Status status;
    private final boolean upgraded;

    public ReceiveEverythingAction(Status status, boolean upgraded) {
        this.upgraded = upgraded;
        this.status = status;
    }

    @Override
    public void update() {
        this.addToBot(new ReceiveCardsAction(status.drawPile, status.hand, status.discardPile, status.exhaustPile, upgraded));
        this.addToBot(new ReceivePlayerStatusAction(status));
        this.isDone = true;
    }
}
