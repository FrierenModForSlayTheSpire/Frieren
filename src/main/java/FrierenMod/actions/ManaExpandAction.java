package FrierenMod.actions;

import FrierenMod.cards.optionCards.DoubleMagicInDiscardPile;
import FrierenMod.cards.optionCards.DoubleMagicInDrawPile;
import FrierenMod.cards.optionCards.DoubleMagicInHand;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public class ManaExpandAction extends AbstractGameAction {
    private final boolean upgraded;

    public ManaExpandAction(boolean upgraded) {
        this.upgraded = upgraded;
    }

    @Override
    public void update() {
        if(this.upgraded){
            this.addToBot(new DoubleMagicInHandAction());
            this.addToBot(new DoubleMagicInDrawPileAction());
            this.addToBot(new DoubleMagicInDiscardPileAction());
        }else {
            ArrayList<AbstractCard> choices = new ArrayList<>();
            choices.add(new DoubleMagicInDrawPile());
            choices.add(new DoubleMagicInHand());
            choices.add(new DoubleMagicInDiscardPile());
            this.addToBot(new ChooseOneAction(choices));
        }
        this.isDone = true;
    }
}
