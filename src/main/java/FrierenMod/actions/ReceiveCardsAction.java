package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public class ReceiveCardsAction extends AbstractGameAction {
    private final ArrayList<AbstractCard> drawPile;
    private final ArrayList<AbstractCard> hand;
    private final ArrayList<AbstractCard> discardPile;
    private final ArrayList<AbstractCard> exhaustPile;
    private final boolean isChanged;

    public ReceiveCardsAction(ArrayList<AbstractCard> drawPile, ArrayList<AbstractCard> hand, ArrayList<AbstractCard> discardPile, ArrayList<AbstractCard> exhaustPile, boolean isChanged) {
        this.drawPile = drawPile;
        this.hand = hand;
        this.discardPile = discardPile;
        this.exhaustPile = exhaustPile;
        this.isChanged = isChanged;
    }
    @Override
    public void update() {
        this.addToBot(new DestroyAllCardsAction(new MakeCardsAction(drawPile,hand,discardPile,exhaustPile,isChanged)));
        this.isDone = true;
    }
}
