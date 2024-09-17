package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Regret;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class MakeCardsAction extends AbstractGameAction {
    private final ArrayList<AbstractCard> drawPile;
    private final ArrayList<AbstractCard> hand;
    private final ArrayList<AbstractCard> discardPile;
    private final ArrayList<AbstractCard> exhaustPile;
    private boolean upgraded;

    public MakeCardsAction(ArrayList<AbstractCard> drawPile, ArrayList<AbstractCard> hand, ArrayList<AbstractCard> discardPile, ArrayList<AbstractCard> exhaustPile, boolean upgraded) {
        this.drawPile = drawPile;
        this.hand = hand;
        this.discardPile = discardPile;
        this.exhaustPile = exhaustPile;
        this.upgraded = upgraded;
    }

    public MakeCardsAction(ArrayList<AbstractCard> drawPile, ArrayList<AbstractCard> hand, ArrayList<AbstractCard> discardPile, ArrayList<AbstractCard> exhaustPile) {
        this.drawPile = drawPile;
        this.hand = hand;
        this.discardPile = discardPile;
        this.exhaustPile = exhaustPile;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        if (this.drawPile != null)
            for (AbstractCard c : this.drawPile) {
                p.drawPile.addToTop(c);
                c.unfadeOut();
                c.lighten(true);
            }
        if (this.hand != null)
            for (AbstractCard c : this.hand) {
                p.hand.addToHand(c);
                c.unfadeOut();
                c.lighten(true);
            }
        if (this.discardPile != null)
            for (AbstractCard c : this.discardPile) {
                p.discardPile.addToTop(c);
                c.unfadeOut();
                c.lighten(true);
            }
        if (this.exhaustPile != null)
            for (AbstractCard c : this.exhaustPile) {
                p.exhaustPile.addToTop(c);
                c.unfadeOut();
                c.lighten(true);
            }
        AbstractCard card = new Regret();
        if (!upgraded)
            this.addToBot(new MakeTempCardInHandAction(card));
        this.isDone = true;
    }
}
