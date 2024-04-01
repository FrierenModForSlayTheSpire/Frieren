package FrierenMod.actions;

import FrierenMod.cards.canAutoAdd.optionCards.ChantDiscardPile;
import FrierenMod.cards.canAutoAdd.optionCards.ChantDrawPile;
import FrierenMod.cards.canAutoAdd.optionCards.ChantHand;
import FrierenMod.gameHelpers.CombatHelper;
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
        AbstractCard c1 = initChoiceCard(1);
        AbstractCard c2 = initChoiceCard(2);
        AbstractCard c3 = initChoiceCard(3);
        if(!AbstractDungeon.player.hasPower(CHANT_WITHOUT_MANA)){
            if (CombatHelper.canChantFromDrawPile(this.x)) {
                stanceChoices.add(c1);
            }
            if (CombatHelper.canChantFromHand(this.x)) {
                stanceChoices.add(c2);
            }
            if (CombatHelper.canChantFromDiscardPile(this.x)) {
                stanceChoices.add(c3);
            }
        }
        else {
            stanceChoices.add(c1);
            stanceChoices.add(c2);
            stanceChoices.add(c3);
        }
        if (!stanceChoices.isEmpty()) {
            this.addToTop(new ChooseOneAction(stanceChoices));
        }
        this.isDone = true;
    }
    private AbstractCard initChoiceCard(int type){
        switch (type){
            case 1:
                ChantDrawPile c1;
                if(nextAction != null)
                    c1 = new ChantDrawPile(nextAction);
                else {
                    c1 = new ChantDrawPile();
                }
                c1.block = c1.baseBlock = x;
                c1.magicNumber = c1.baseMagicNumber = x;
                if(AbstractDungeon.player.hasPower(CHANT_WITHOUT_MANA)){
                    c1.upgrade();
                    c1.upgraded = true;
                }
                c1.applyPowers();
                return c1;
            case 2:
                ChantHand c2;
                if(nextAction != null){
                    c2 = new ChantHand(cardToReturn,nextAction);
                }else {
                    c2 = new ChantHand(cardToReturn);
                }
                c2.magicNumber = c2.baseMagicNumber = x;
                if(AbstractDungeon.player.hasPower(CHANT_WITHOUT_MANA)){
                    c2.upgrade();
                    c2.upgraded = true;
                }
                return c2;
            case 3:
                ChantDiscardPile c3;
                if(nextAction != null){
                    c3 = new ChantDiscardPile(nextAction);
                }
                else {
                    c3 = new ChantDiscardPile();
                }
                c3.magicNumber = c3.baseMagicNumber = x;
                if(AbstractDungeon.player.hasPower(CHANT_WITHOUT_MANA)){
                    c3.upgrade();
                    c3.upgraded = true;
                }
                return c3;
            default:
                System.out.println("ERROR");
                return null;
        }
    }
}