package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import java.util.ArrayList;

public class ExchangeDrawAndDiscardPileAction extends AbstractGameAction {
    private final AbstractPlayer p;

    public ExchangeDrawAndDiscardPileAction(AbstractPlayer p) {
        this.p = p;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> drawPile = new ArrayList<>(p.drawPile.group);
        ArrayList<AbstractCard> discardPile = new ArrayList<>(p.discardPile.group);
        p.drawPile.clear();
        p.discardPile.clear();
        for (AbstractCard c : drawPile) {
            p.discardPile.addToTop(c);
            c.unfadeOut();
            c.lighten(true);
        }
        for (AbstractCard c : discardPile) {
            p.drawPile.addToTop(c);
            c.unfadeOut();
            c.lighten(true);
        }
        this.isDone = true;
    }
}
