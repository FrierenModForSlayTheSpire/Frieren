package FrierenMod.actions;

import FrierenMod.cards.optionCards.DoubleMagicInDiscardPile;
import FrierenMod.cards.optionCards.DoubleMagicInDrawPile;
import FrierenMod.cards.optionCards.DoubleMagicInHand;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public class MagicExpandAction extends AbstractGameAction {
    private final boolean upgraded;
    private final boolean canGainMagic;

    public MagicExpandAction(boolean upgraded, boolean canGainMagic) {
        this.upgraded = upgraded;
        this.canGainMagic = canGainMagic;
    }

    @Override
    public void update() {
        if(this.upgraded){
            this.addToBot(new DoubleMagicInHandAction(canGainMagic));
            this.addToBot(new DoubleMagicInDrawPileAction(canGainMagic));
            this.addToBot(new DoubleMagicInDiscardPileAction(canGainMagic));
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
