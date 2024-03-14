package FrierenMod.actions;

import FrierenMod.cards.optionCards.ChantDiscardPile;
import FrierenMod.cards.optionCards.ChantDrawPile;
import FrierenMod.cards.optionCards.ChantHand;
import FrierenMod.gameHelpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

import static FrierenMod.gameHelpers.HardCodedPowerHelper.CHANT_WITHOUT_MAGIC;

public class ChantAction extends AbstractGameAction {
    private final int x;
    private final boolean giveCard;
    private AbstractGameAction[] nextAction;
    public ChantAction(int x) {
        this.x = x;
        this.giveCard = false;
        this.actionType = ActionType.WAIT;
    }
    public ChantAction(int x, boolean giveCard) {
        this.x = x;
        this.giveCard = giveCard;
        this.actionType = ActionType.WAIT;
    }
    public ChantAction(int x, boolean giveCard, AbstractGameAction... nextAction) {
        this.x = x;
        this.giveCard = giveCard;
        this.actionType = ActionType.WAIT;
        this.nextAction = nextAction;
    }
    @Override
    public void update() {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        if(!AbstractDungeon.player.hasPower(CHANT_WITHOUT_MAGIC)){
            if (ChantHelper.canChantFromDrawPile(this.x)) {
                ChantDrawPile c;
                if(nextAction != null)
                    c = new ChantDrawPile(nextAction);
                else {
                    c = new ChantDrawPile();
                }
                c.block = c.baseBlock = x;
                c.magicNumber = c.baseMagicNumber = x;
                c.applyPowers();
                stanceChoices.add(c);
            }
            if (ChantHelper.canChantFromHand(this.x)) {
                ChantHand c;
                if(nextAction != null){
                    c = new ChantHand(giveCard,nextAction);
                }else {
                    c = new ChantHand(giveCard);
                }
                c.magicNumber = c.baseMagicNumber = x;
                stanceChoices.add(c);
            }
            if (ChantHelper.canChantFromDiscardPile(this.x)) {
                ChantDiscardPile c;
                if(nextAction != null){
                    c = new ChantDiscardPile(nextAction);
                }
                else {
                    c = new ChantDiscardPile();
                }
                c.magicNumber = c.baseMagicNumber = x;
                stanceChoices.add(c);
            }

        }
        else {
            ChantDrawPile c1;
            if(nextAction != null)
                c1 = new ChantDrawPile(nextAction);
            else {
                c1 = new ChantDrawPile();
            }
            c1.block = c1.baseBlock = x;
            c1.magicNumber = c1.baseMagicNumber = x;
            c1.upgrade();
            c1.upgraded = true;
            c1.applyPowers();
            ChantHand c2;
            if(nextAction != null){
                c2 = new ChantHand(giveCard,nextAction);
            }else {
                c2 = new ChantHand(giveCard);
            }
            c2.magicNumber = c2.baseMagicNumber = x;
            c2.upgrade();
            c2.upgraded = true;
            ChantDiscardPile c3;
            if(nextAction != null){
                c3 = new ChantDiscardPile(nextAction);
            }
            else {
                c3 = new ChantDiscardPile();
            }
            c3.magicNumber = c3.baseMagicNumber = x;
            c3.upgrade();
            c3.upgraded = true;
            stanceChoices.add(c1);
            stanceChoices.add(c2);
            stanceChoices.add(c3);
        }
        if (!stanceChoices.isEmpty()) {
            this.addToTop(new ChooseOneAction(stanceChoices));
        }
        this.isDone = true;
    }
}