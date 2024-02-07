package FrierenMod.actions;

import FrierenMod.helpers.Status;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;

public class ReceiveEverythingAction extends AbstractGameAction {
    private final Status status;
    private final boolean isChanged;
    public ReceiveEverythingAction(Status status,boolean isChanged){
        this.isChanged = isChanged;
        this.status = status;
    }
    @Override
    public void update() {
        this.addToBot(new ReceiveCardsAction(status.drawPile,status.hand,status.discardPile,status.exhaustPile,isChanged));
        this.addToBot(new ReceivePlayerStatusAction(status));
        this.isDone = true;
    }
}