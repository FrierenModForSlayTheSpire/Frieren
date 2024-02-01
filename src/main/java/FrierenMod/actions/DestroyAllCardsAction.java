package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class DestroyAllCardsAction extends AbstractGameAction {
    private final AbstractGameAction nextAction;
    public DestroyAllCardsAction(AbstractGameAction nextAction){
        this.nextAction = nextAction;
    }
    @Override
    public void update() {
        this.addToBot(new DestroyAllCardsInDrawPileAction());
        this.addToBot(new DestoryAllCardsInHandAction());
        this.addToBot(new DestroyAllCardsInDiscardPileAction());
        this.addToBot(new DestroyAllCardsInExhaustPileAction());
        this.addToBot(nextAction);
        this.isDone = true;
    }
}
