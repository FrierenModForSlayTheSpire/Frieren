package FrierenMod.actions;

import FrierenMod.cards.optionCards.MagicFactors.AbstractMagicFactor;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.gameHelpers.MagicFactorHelper;
import FrierenMod.utils.Log;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class ChantAction extends AbstractGameAction {
    private int chantX;
    private int manaNeed;
    private AbstractGameAction[] nextAction;
    private AbstractCard cardToReturn;
    private Integer blockGain = null;
    private final ChantType chantType;
    private final AbstractMagicFactor[] chantChoices = new AbstractMagicFactor[3];
    ;

    public ChantAction(int chantX) {
        this.chantX = chantX;
        this.manaNeed = CombatHelper.getManaNeedWhenChant(chantX);
        this.actionType = ActionType.WAIT;
        this.cardToReturn = null;
        this.nextAction = null;
        this.chantType = ChantType.NORMAL;
    }

    public ChantAction(int chantX, int blockGain) {
        this.chantX = chantX;
        this.manaNeed = CombatHelper.getManaNeedWhenChant(chantX);
        this.actionType = ActionType.WAIT;
        this.cardToReturn = null;
        this.nextAction = null;
        this.blockGain = blockGain;
        this.chantType = ChantType.NORMAL;
    }

    public ChantAction(int chantX, AbstractGameAction... nextAction) {
        this.chantX = chantX;
        this.manaNeed = CombatHelper.getManaNeedWhenChant(chantX);
        this.actionType = ActionType.WAIT;
        this.cardToReturn = null;
        this.nextAction = nextAction;
        this.chantType = ChantType.NORMAL;
    }

    public ChantAction(int chantX, AbstractCard cardToReturn) {
        this.chantX = chantX;
        this.manaNeed = CombatHelper.getManaNeedWhenChant(chantX);
        this.actionType = ActionType.WAIT;
        this.cardToReturn = cardToReturn;
        this.nextAction = null;
        this.chantType = ChantType.NORMAL;
    }

    public ChantAction(int chantX, AbstractCard cardToReturn, AbstractGameAction... nextAction) {
        this.chantX = chantX;
        this.manaNeed = CombatHelper.getManaNeedWhenChant(chantX);
        this.actionType = ActionType.WAIT;
        this.cardToReturn = cardToReturn;
        this.nextAction = nextAction;
        this.chantType = ChantType.NORMAL;
    }

    public ChantAction(ChantType type) {
        this.chantType = type;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> stanceChoices;
        int hand = CombatHelper.getManaNumInHand();
        int draw = CombatHelper.getManaNumInDrawPile();
        int discard = CombatHelper.getManaNumInDiscardPile();
        initializeChantChoices();
        switch (this.chantType) {
            case NORMAL:
                stanceChoices = new ArrayList<>();
                if (CombatHelper.canChantFromDrawPile(this.manaNeed) && chantChoices[0] != null) {
                    chantChoices[0].loadMagicFactor(nextAction, manaNeed, chantX);
                    if (this.blockGain != null)
                        chantChoices[0].extraActions.add(new GainBlockAction(AbstractDungeon.player, this.blockGain));
                    stanceChoices.add(chantChoices[0]);
                }
                if (CombatHelper.canChantFromHand(this.manaNeed) && chantChoices[1] != null) {
                    chantChoices[1].loadMagicFactor(nextAction, manaNeed, chantX);
                    if (cardToReturn != null) {
                        cardToReturn.returnToHand = true;
                        cardToReturn.upgrade();
                    }
                    stanceChoices.add(chantChoices[1]);
                }
                if (CombatHelper.canChantFromDiscardPile(this.manaNeed) && chantChoices[2] != null) {
                    chantChoices[2].loadMagicFactor(nextAction, manaNeed, chantX);
                    stanceChoices.add(chantChoices[2]);
                }
                if (!stanceChoices.isEmpty()) {
                    this.addToTop(new ChooseOneAction(stanceChoices));
                }
                break;
            case PRECISE:
                stanceChoices = new ArrayList<>();
                if (draw > 0 && chantChoices[0] != null) {
                    chantChoices[0].loadMagicFactor(CombatHelper.getManaNeedWhenChant(draw), draw);
                    stanceChoices.add(chantChoices[0]);
                }
                if (hand > 0 && chantChoices[1] != null) {
                    chantChoices[1].loadMagicFactor(CombatHelper.getManaNeedWhenChant(hand), hand);
                    stanceChoices.add(chantChoices[1]);
                }
                if (discard > 0 && chantChoices[2] != null) {
                    chantChoices[2].loadMagicFactor(CombatHelper.getManaNeedWhenChant(discard), discard);
                    stanceChoices.add(chantChoices[2]);
                }
                if (!stanceChoices.isEmpty()) {
                    this.addToTop(new ChooseOneAction(stanceChoices));
                }
                break;
            case FINAL:
                stanceChoices = new ArrayList<>();
                if (draw > 0 && chantChoices[0] != null) {
                    chantChoices[0].loadMagicFactor(CombatHelper.getManaNeedWhenChant(draw), draw);
                    stanceChoices.add(chantChoices[0]);
                }
                if (hand > 0 && chantChoices[1] != null) {
                    chantChoices[1].loadMagicFactor(CombatHelper.getManaNeedWhenChant(hand), hand);
                    stanceChoices.add(chantChoices[1]);
                }
                if (discard > 0 && chantChoices[2] != null) {
                    chantChoices[2].loadMagicFactor(CombatHelper.getManaNeedWhenChant(discard), discard);
                    stanceChoices.add(chantChoices[2]);
                }
                if (!stanceChoices.isEmpty()) {
                    boolean haveTriggered = false;
                    for(AbstractCard f:stanceChoices){
                        if(f instanceof AbstractMagicFactor){
                            ((AbstractMagicFactor) f).takeEffect();
                            if(!haveTriggered){
                                ((AbstractMagicFactor) f).triggerPowers();
                                ((AbstractMagicFactor) f).triggerCards();
                                haveTriggered = true;
                            }
                        }
                    }
                }
                break;
            default:
                Log.logger.info("ERROR: Unknown Chant Type !!!");
                break;
        }
        this.isDone = true;
    }

    protected void initializeChantChoices() {
        for (AbstractMagicFactor f : MagicFactorHelper.getAllFactors()) {
            if (f != null && f.currentSlot == 0) {
                this.chantChoices[0] = f;
                break;
            }
        }
        for (AbstractMagicFactor f : MagicFactorHelper.getAllFactors()) {
            if (f != null && f.currentSlot == 1) {
                this.chantChoices[1] = f;
                break;
            }
        }
        for (AbstractMagicFactor f : MagicFactorHelper.getAllFactors()) {
            if (f != null && f.currentSlot == 2) {
                this.chantChoices[2] = f;
                break;
            }
        }
    }
    public enum ChantType {
        NORMAL,
        PRECISE,
        FINAL
    }
}