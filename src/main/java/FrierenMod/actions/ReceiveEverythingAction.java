package FrierenMod.actions;

import FrierenMod.gameHelpers.State;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class ReceiveEverythingAction extends AbstractGameAction {
    private final State state;
    private final boolean upgraded;

    public ReceiveEverythingAction(State state, boolean upgraded) {
        this.upgraded = upgraded;
        this.state = state;
    }

    @Override
    public void update() {
        this.addToBot(new ReceiveCardsAction(state.drawPile, state.hand, state.discardPile, state.exhaustPile, upgraded));
        this.addToBot(new ReceivePlayerStateAction(state));
        this.isDone = true;
    }
}
