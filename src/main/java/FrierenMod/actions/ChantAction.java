package FrierenMod.actions;

import FrierenMod.cards.canAutoAdd.optionCards.ChantDiscardPile;
import FrierenMod.cards.canAutoAdd.optionCards.ChantDrawPile;
import FrierenMod.cards.canAutoAdd.optionCards.ChantHand;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.Log;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

import static FrierenMod.gameHelpers.HardCodedPowerHelper.CHANT_WITHOUT_MANA;

public class ChantAction extends AbstractGameAction {
    private final int x;
    private AbstractGameAction[] nextAction;
    private final AbstractCard cardToReturn;

    public ChantAction(int x) {
        this.x = x;
        this.actionType = ActionType.WAIT;
        this.cardToReturn = null;
    }

    public ChantAction(int x, AbstractGameAction... nextAction) {
        this.x = x;
        this.actionType = ActionType.WAIT;
        this.nextAction = nextAction;
        this.cardToReturn = null;
    }

    public ChantAction(int x, AbstractCard cardToReturn) {
        this.x = x;
        this.cardToReturn = cardToReturn;
        this.actionType = ActionType.WAIT;
    }

    public ChantAction(int x, AbstractCard cardToReturn, AbstractGameAction... nextAction) {
        this.x = x;
        this.actionType = ActionType.WAIT;
        this.nextAction = nextAction;
        this.cardToReturn = cardToReturn;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        AbstractCard c1 = initChoiceCard(CardType.DRAW_PILE);
        AbstractCard c2 = initChoiceCard(CardType.HAND);
        AbstractCard c3 = initChoiceCard(CardType.DISCARD_PILE);
        if (!CombatHelper.canFreeChant(this.x)) {
            if (CombatHelper.canChantFromDrawPile(this.x)) {
                stanceChoices.add(c1);
            }
            if (CombatHelper.canChantFromHand(this.x)) {
                stanceChoices.add(c2);
            }
            if (CombatHelper.canChantFromDiscardPile(this.x)) {
                stanceChoices.add(c3);
            }
        } else {
            stanceChoices.add(c1);
            stanceChoices.add(c2);
            stanceChoices.add(c3);
        }
        if (!stanceChoices.isEmpty()) {
            this.addToTop(new ChooseOneAction(stanceChoices));
        }
        this.isDone = true;
    }

    private AbstractCard initChoiceCard(CardType type) {
        switch (type) {
            case DRAW_PILE:
                ChantDrawPile c1 = (nextAction == null ? new ChantDrawPile() : new ChantDrawPile(nextAction));
                c1.block = c1.baseBlock = x;
                c1.magicNumber = c1.baseMagicNumber = x;
                if (CombatHelper.canFreeChant(x)) {
                    c1.upgrade();
                    c1.upgraded = true;
                }
                c1.applyPowers();
                return c1;
            case HAND:
                ChantHand c2 = (nextAction == null ? new ChantHand(cardToReturn) : new ChantHand(cardToReturn, nextAction));
                c2.magicNumber = c2.baseMagicNumber = x;
                if (CombatHelper.canFreeChant(x)) {
                    c2.upgrade();
                    c2.upgraded = true;
                }
                return c2;
            case DISCARD_PILE:
                ChantDiscardPile c3 = (nextAction == null ? new ChantDiscardPile() : new ChantDiscardPile(nextAction));
                c3.magicNumber = c3.baseMagicNumber = x;
                if (CombatHelper.canFreeChant(x)) {
                    c3.upgrade();
                    c3.upgraded = true;
                }
                return c3;
            default:
                Log.logger.info("WTF?");
                return null;
        }
    }

    private enum CardType {
        DRAW_PILE,
        HAND,
        DISCARD_PILE
    }
}