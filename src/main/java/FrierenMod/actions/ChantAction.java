package FrierenMod.actions;

import FrierenMod.cards.optionCards.magicItems.AbstractMagicItem;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.patches.fields.MagicItemBagField;
import FrierenMod.utils.Log;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class ChantAction extends AbstractGameAction {
    private int chantX;
    private AbstractGameAction[] nextAction;
    private AbstractCard cardToReturn;
    private Integer blockGain = null;
    private final ChantType chantType;
    private final AbstractMagicItem[] chantChoices = new AbstractMagicItem[3];
    ;

    public ChantAction(int chantX) {
        this.chantX = chantX;
        this.actionType = ActionType.WAIT;
        this.chantType = ChantType.NORMAL;
    }

    public ChantAction(int chantX, int blockGain) {
        this(chantX);
        this.blockGain = blockGain;
    }

    public ChantAction(int chantX, AbstractGameAction... nextAction) {
        this(chantX);
        this.nextAction = nextAction;
    }

    public ChantAction(int chantX, AbstractCard cardToReturn) {
        this(chantX);
        this.cardToReturn = cardToReturn;
    }

    public ChantAction(int chantX, AbstractCard cardToReturn, AbstractGameAction... nextAction) {
        this(chantX, cardToReturn);
        this.nextAction = nextAction;
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
                if (chantChoices[0] != null && CombatHelper.canChantFromDrawPile(getManaNeed(chantChoices[0]))) {
                    chantChoices[0].loadMagicFactor(nextAction, getManaNeed(chantChoices[0]), chantX);
                    if (this.blockGain != null)
                        chantChoices[0].extraActions.add(new GainBlockAction(AbstractDungeon.player, this.blockGain));
                    stanceChoices.add(chantChoices[0]);
                }
                if (chantChoices[1] != null && CombatHelper.canChantFromHand(getManaNeed(chantChoices[1]))) {
                    chantChoices[1].loadMagicFactor(nextAction, getManaNeed(chantChoices[1]), chantX);
                    if (cardToReturn != null) {
                        cardToReturn.returnToHand = true;
                        cardToReturn.upgrade();
                    }
                    stanceChoices.add(chantChoices[1]);
                }
                if (chantChoices[2] != null && CombatHelper.canChantFromDiscardPile(getManaNeed(chantChoices[2]))) {
                    chantChoices[2].loadMagicFactor(nextAction, getManaNeed(chantChoices[2]), chantX);
                    stanceChoices.add(chantChoices[2]);
                }
                if (!stanceChoices.isEmpty()) {
                    this.addToTop(new ChooseOneAction(stanceChoices));
                }
                break;
            case PRECISE:
            case FINAL:
                stanceChoices = new ArrayList<>();
                if (chantChoices[0] != null) {
                    int chantX = getChantX(draw, chantChoices[0]);
                    int manaNeed = getManaNeed(draw, chantChoices[0]);
                    if (CombatHelper.canChantFromDrawPile(manaNeed)) {
                        chantChoices[0].loadMagicFactor(manaNeed, chantX);
                        stanceChoices.add(chantChoices[0]);
                    }
                }
                if (chantChoices[1] != null) {
                    int chantX = getChantX(hand, chantChoices[1]);
                    int manaNeed = getManaNeed(hand, chantChoices[1]);
                    if (CombatHelper.canChantFromHand(manaNeed)) {
                        chantChoices[1].loadMagicFactor(manaNeed, chantX);
                        stanceChoices.add(chantChoices[1]);
                    }
                }
                if (chantChoices[2] != null) {
                    int chantX = getChantX(discard, chantChoices[2]);
                    int manaNeed = getManaNeed(discard, chantChoices[2]);
                    if (CombatHelper.canChantFromDiscardPile(manaNeed)) {
                        chantChoices[2].loadMagicFactor(manaNeed, chantX);
                        stanceChoices.add(chantChoices[2]);
                    }
                }
                if (this.chantType == ChantType.PRECISE) {
                    if (!stanceChoices.isEmpty())
                        this.addToTop(new ChooseOneAction(stanceChoices));
                } else {
                    if (!stanceChoices.isEmpty()) {
                        boolean haveTriggered = false;
                        for (AbstractCard f : stanceChoices) {
                            if (f instanceof AbstractMagicItem) {
                                ((AbstractMagicItem) f).takeEffect();
                                if (!haveTriggered) {
                                    ((AbstractMagicItem) f).triggerPowers();
                                    ((AbstractMagicItem) f).triggerCards();
                                    haveTriggered = true;
                                }
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
        for (AbstractCard c : MagicItemBagField.getBag().group) {
            if (c instanceof AbstractMagicItem && ((AbstractMagicItem) c).currentSlot == 0) {
                this.chantChoices[0] = (AbstractMagicItem) c.makeStatEquivalentCopy();
            }
            if (c instanceof AbstractMagicItem && ((AbstractMagicItem) c).currentSlot == 1) {
                this.chantChoices[1] = (AbstractMagicItem) c.makeStatEquivalentCopy();
            }
            if (c instanceof AbstractMagicItem && ((AbstractMagicItem) c).currentSlot == 2) {
                this.chantChoices[2] = (AbstractMagicItem) c.makeStatEquivalentCopy();
            }
        }
    }

    public enum ChantType {
        NORMAL,
        PRECISE,
        FINAL
    }

    private int getManaNeed(AbstractMagicItem f) {
        return CombatHelper.getManaNeedWhenChant(this.chantX * f.manaNeedMultipleCoefficient + f.manaNeedAddCoefficient);
    }

    private static int getManaNeed(int chantX, AbstractMagicItem f) {
        return CombatHelper.getManaNeedWhenChant(chantX * f.manaNeedMultipleCoefficient + f.manaNeedAddCoefficient);
    }

    private static int getChantX(int manaAmt, AbstractMagicItem f) {
        return (manaAmt - f.manaNeedAddCoefficient) / f.manaNeedMultipleCoefficient;
    }

}