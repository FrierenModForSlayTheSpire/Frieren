package FrierenMod.actions;

import FrierenMod.cards.optionCards.ChantOptions.ChantDiscardPile;
import FrierenMod.cards.optionCards.ChantOptions.ChantDrawPile;
import FrierenMod.cards.optionCards.ChantOptions.ChantHand;
import FrierenMod.gameHelpers.CombatHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public class ChantAction extends AbstractGameAction {
    private final int chantX;
    private final int manaNeed;
    private final AbstractGameAction[] nextAction;
    private final AbstractCard cardToReturn;
    private int blockGain = 0;

    public ChantAction(int chantX) {
        this.chantX = chantX;
        this.manaNeed = CombatHelper.getManaNeedWhenChant(chantX);
        this.actionType = ActionType.WAIT;
        this.cardToReturn = null;
        this.nextAction = null;
    }

    public ChantAction(int chantX, int blockGain) {
        this.chantX = chantX;
        this.manaNeed = CombatHelper.getManaNeedWhenChant(chantX);
        this.actionType = ActionType.WAIT;
        this.cardToReturn = null;
        this.nextAction = null;
        this.blockGain = blockGain;
    }

    public ChantAction(int chantX, AbstractGameAction... nextAction) {
        this.chantX = chantX;
        this.manaNeed = CombatHelper.getManaNeedWhenChant(chantX);
        this.actionType = ActionType.WAIT;
        this.cardToReturn = null;
        this.nextAction = nextAction;
    }

    public ChantAction(int chantX, AbstractCard cardToReturn) {
        this.chantX = chantX;
        this.manaNeed = CombatHelper.getManaNeedWhenChant(chantX);
        this.actionType = ActionType.WAIT;
        this.cardToReturn = cardToReturn;
        this.nextAction = null;
    }

    public ChantAction(int chantX, AbstractCard cardToReturn, AbstractGameAction... nextAction) {
        this.chantX = chantX;
        this.manaNeed = CombatHelper.getManaNeedWhenChant(chantX);
        this.actionType = ActionType.WAIT;
        this.cardToReturn = cardToReturn;
        this.nextAction = nextAction;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        if (CombatHelper.canChantFromDrawPile(this.manaNeed)) {
            stanceChoices.add(new ChantDrawPile(this.manaNeed, this.chantX, this.blockGain, this.nextAction));
        }
        if (CombatHelper.canChantFromHand(this.manaNeed)) {
            stanceChoices.add(new ChantHand(this.manaNeed, this.chantX, this.cardToReturn, this.nextAction));
        }
        if (CombatHelper.canChantFromDiscardPile(this.manaNeed)) {
            stanceChoices.add(new ChantDiscardPile(this.manaNeed, this.chantX, this.nextAction));
        }
        if (!stanceChoices.isEmpty()) {
            this.addToTop(new ChooseOneAction(stanceChoices));
        }
        this.isDone = true;
    }
}