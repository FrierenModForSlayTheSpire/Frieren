package FrierenMod.actions;

import FrierenMod.cards.optionCards.ChantOptions.ChantDiscardPile;
import FrierenMod.cards.optionCards.ChantOptions.ChantDrawPile;
import FrierenMod.cards.optionCards.ChantOptions.ChantHand;
import FrierenMod.gameHelpers.CombatHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public class PreciseChantAction extends AbstractGameAction {

    @Override
    public void update() {
        int hand = CombatHelper.getManaNumInHand();
        int draw = CombatHelper.getManaNumInDrawPile();
        int discard = CombatHelper.getManaNumInDiscardPile();
        ArrayList<AbstractCard> choices = new ArrayList<>();
        if (draw > 0) {
            ChantDrawPile c = new ChantDrawPile(CombatHelper.getManaNeedWhenChant(draw), draw);
            choices.add(c);
        }
        if (hand > 0) {
            ChantHand c = new ChantHand(CombatHelper.getManaNeedWhenChant(hand), hand);
            choices.add(c);
        }
        if (discard > 0) {
            ChantDiscardPile c = new ChantDiscardPile(CombatHelper.getManaNeedWhenChant(discard), discard);
            choices.add(c);
        }
        if (!choices.isEmpty()) {
            this.addToTop(new ChooseOneAction(choices));
        }
        this.isDone = true;
    }
}
