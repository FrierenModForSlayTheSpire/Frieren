package FrierenMod.actions;

import FrierenMod.cards.canAutoAdd.optionCards.DoubleManaInDiscardPile;
import FrierenMod.cards.canAutoAdd.optionCards.DoubleManaInDrawPile;
import FrierenMod.cards.canAutoAdd.optionCards.DoubleManaInHand;
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
            this.addToBot(new DoubleManaInHandAction());
            this.addToBot(new DoubleManaInDrawPileAction());
            this.addToBot(new DoubleManaInDiscardPileAction());
        }else {
            ArrayList<AbstractCard> choices = new ArrayList<>();
            choices.add(new DoubleManaInDrawPile());
            choices.add(new DoubleManaInHand());
            choices.add(new DoubleManaInDiscardPile());
            this.addToBot(new ChooseOneAction(choices));
        }
        this.isDone = true;
    }
}
