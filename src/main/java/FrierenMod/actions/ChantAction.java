package FrierenMod.actions;

import FrierenMod.cards.optionCards.magicItems.AbstractMagicItem;
import FrierenMod.gameHelpers.CombatHelper;
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
        AbstractMagicItem[] chantChoices = CombatHelper.getChantChoices();
        switch (this.chantType) {
            case NORMAL:
                stanceChoices = new ArrayList<>();
                if (CombatHelper.canActivateSlot(chantX, 0)) {
                    chantChoices[0].loadMagicFactor(chantX, nextAction);
                    if (this.blockGain != null)
                        chantChoices[0].extraActions.add(new GainBlockAction(AbstractDungeon.player, this.blockGain));
                    stanceChoices.add(chantChoices[0]);
                }
                if (CombatHelper.canActivateSlot(chantX, 1)) {
                    chantChoices[1].loadMagicFactor(chantX, nextAction);
                    if (cardToReturn != null) {
                        chantChoices[1].immediateActions.add(() -> {
                            cardToReturn.returnToHand = true;
                            cardToReturn.upgrade();
                        });
                    }
                    stanceChoices.add(chantChoices[1]);
                }
                if (CombatHelper.canActivateSlot(chantX, 2)) {
                    chantChoices[2].loadMagicFactor(chantX, nextAction);
                    stanceChoices.add(chantChoices[2]);
                }
                if (!stanceChoices.isEmpty()) {
                    this.addToTop(new ChooseOneAction(stanceChoices));
                }
                break;
            case PRECISE:
            case FINAL:
                stanceChoices = new ArrayList<>();
                int[] manaNums = new int[3];
                manaNums[0] = CombatHelper.getManaNumInDrawPile();
                manaNums[1] = CombatHelper.getManaNumInHand();
                manaNums[2] = CombatHelper.getManaNumInDiscardPile();
                for (int i = 0; i < 3; i++) {
                    int chantX = getChantX(manaNums[i], chantChoices[i]);
                    if (CombatHelper.canActivateSlot(chantX, i) && chantX > 0) {
                        chantChoices[i].loadMagicFactor(chantX);
                        stanceChoices.add(chantChoices[i]);
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
                                f.onChoseThisOption();
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

    public enum ChantType {
        NORMAL,
        PRECISE,
        FINAL
    }

    private static int getChantX(int manaAmt, AbstractMagicItem f) {
        return (manaAmt - f.manaNeedAddCoefficient) / f.manaNeedMultipleCoefficient;
    }

}