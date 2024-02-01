package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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
        for(AbstractCard c: this.drawPile){
            this.addToBot(new MakeTempCardInDrawPileAction(c,1,false,false,isChanged));
        }
        for(AbstractCard c: this.hand){
            this.addToBot(new MakeTempCardInHandAction(c));
        }
        for(AbstractCard c: this.discardPile){
            this.addToBot(new MakeTempCardInDiscardAction(c,1));
        }
        for(AbstractCard c: this.exhaustPile){
            AbstractDungeon.player.exhaustPile.addToHand(c);
        }
        this.isDone = true;
    }
}
