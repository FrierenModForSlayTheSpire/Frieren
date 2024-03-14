package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DestroyAllCardsAction extends AbstractGameAction {
    private final AbstractGameAction nextAction;
    public DestroyAllCardsAction(AbstractGameAction nextAction){
        this.nextAction = nextAction;
    }
    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        p.drawPile.clear();
        this.destroyAllCardsInCardGroup(p.hand);
        p.discardPile.clear();
        p.exhaustPile.clear();
        this.addToBot(nextAction);
        this.isDone = true;
    }
    public void destroyAllCardsInCardGroup(CardGroup cardGroup){
        for (int i = 0; i < cardGroup.size(); i++) {
            AbstractCard card = cardGroup.group.get(i);
            this.addToTop(new DestroySpecifiedCardAction(card,cardGroup));
        }
    }
}
