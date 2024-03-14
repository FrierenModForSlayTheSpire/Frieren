package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class MakeCardsAction extends AbstractGameAction {
    private final ArrayList<AbstractCard> drawPile;
    private final ArrayList<AbstractCard> hand;
    private final ArrayList<AbstractCard> discardPile;
    private final ArrayList<AbstractCard> exhaustPile;
    private final boolean isChanged;
    public MakeCardsAction(ArrayList<AbstractCard> drawPile,ArrayList<AbstractCard> hand, ArrayList<AbstractCard> discardPile, ArrayList<AbstractCard> exhaustPile, boolean isChanged){
        this.drawPile = drawPile;
        this.hand = hand;
        this.discardPile = discardPile;
        this.exhaustPile = exhaustPile;
        this.isChanged = isChanged;
    }
    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        for(AbstractCard c: this.drawPile){
            if(isChanged)
                p.drawPile.addToRandomSpot(c);
            else
                p.drawPile.addToTop(c);
        }
        for(AbstractCard c: this.hand){
            this.addToBot(new MakeTempCardInHandAction(c));
        }
        for(AbstractCard c: this.discardPile){
            p.discardPile.addToTop(c);
        }
        AbstractDungeon.player.exhaustPile.group.addAll(this.exhaustPile);
        this.isDone = true;
    }
}
