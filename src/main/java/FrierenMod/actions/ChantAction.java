package FrierenMod.actions;

import FrierenMod.cards.canAutoAdd.optionCards.ChantDiscardPile;
import FrierenMod.cards.canAutoAdd.optionCards.ChantDrawPile;
import FrierenMod.cards.canAutoAdd.optionCards.ChantHand;
import FrierenMod.gameHelpers.CombatHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public class ChantAction extends AbstractGameAction {
    private final int chantX;
    private final int manaExhaust;
    private final AbstractGameAction[] nextAction;
    private final AbstractCard cardToReturn;

    public ChantAction(int chantX) {
        this.chantX = chantX;
        this.manaExhaust = CombatHelper.getManaExhaustForChantCard(chantX);
        this.actionType = ActionType.WAIT;
        this.cardToReturn = null;
        this.nextAction = null;
    }

    public ChantAction(int chantX, AbstractGameAction... nextAction) {
        this.chantX = chantX;
        this.manaExhaust = CombatHelper.getManaExhaustForChantCard(chantX);
        this.actionType = ActionType.WAIT;
        this.cardToReturn = null;
        this.nextAction = nextAction;
    }

    public ChantAction(int chantX, AbstractCard cardToReturn) {
        this.chantX = chantX;
        this.manaExhaust = CombatHelper.getManaExhaustForChantCard(chantX);
        this.actionType = ActionType.WAIT;
        this.cardToReturn = cardToReturn;
        this.nextAction = null;
    }

    public ChantAction(int chantX, AbstractCard cardToReturn, AbstractGameAction... nextAction) {
        this.chantX = chantX;
        this.manaExhaust = CombatHelper.getManaExhaustForChantCard(chantX);
        this.actionType = ActionType.WAIT;
        this.cardToReturn = cardToReturn;
        this.nextAction = nextAction;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        ChantDrawPile c1 = new ChantDrawPile(this.manaExhaust, this.chantX, this.nextAction);
        ChantHand c2 = new ChantHand(this.manaExhaust, this.chantX, this.cardToReturn, this.nextAction);
        ChantDiscardPile c3 = new ChantDiscardPile(this.manaExhaust, this.chantX, this.nextAction);
        if (CombatHelper.canChantFromDrawPile(this.manaExhaust)) {
            stanceChoices.add(c1);
        }
        if (CombatHelper.canChantFromHand(this.manaExhaust)) {
            stanceChoices.add(c2);
        }
        if (CombatHelper.canChantFromDiscardPile(this.manaExhaust)) {
            stanceChoices.add(c3);
        }
        if (!stanceChoices.isEmpty()) {
            this.addToTop(new ChooseOneAction(stanceChoices));
        }
        this.isDone = true;
    }
}