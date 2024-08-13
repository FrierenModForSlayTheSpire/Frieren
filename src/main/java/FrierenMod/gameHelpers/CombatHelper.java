package FrierenMod.gameHelpers;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.cards.magicItems.factors.BluePrint;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.patches.fields.MagicDeckField;
import FrierenMod.powers.ChantWithoutManaPower;
import FrierenMod.powers.ChantWithoutManaTimesPower;
import FrierenMod.powers.ConcentrationPower;
import FrierenMod.powers.WeakenedChantPower;
import FrierenMod.utils.Log;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;
import java.util.HashSet;

public class CombatHelper {
    public static boolean isInCombat() {
        return (AbstractDungeon.getCurrMapNode() != null && AbstractDungeon.getCurrRoom() != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT);
    }

    public static int getManaNumInDrawPile() {
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c instanceof Mana) {
                counts++;
            }
        }
        return counts;
    }

    public static int getManaNumInHand() {
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof Mana) {
                counts++;
            }
        }
        return counts;
    }

    public static int getManaNumInDiscardPile() {
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c instanceof Mana) {
                counts++;
            }
        }
        return counts;
    }

    public static int getManaNumInExhaustPile() {
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c instanceof Mana) {
                counts++;
            }
        }
        return counts;
    }

    public static int getAllManaNum() {
        return getManaNumInDrawPile() + getManaNumInHand() + getManaNumInDiscardPile();
    }

    public static boolean cannotChant(int chantX) {
        return !canActivateSlot(chantX, 0) && !canActivateSlot(chantX, 1) && !canActivateSlot(chantX, 2);
    }

    public static boolean canActivateSlot(int chantX, int slotNumber) {
        int manaNum;
        switch (slotNumber) {
            case 0:
                manaNum = getManaNumInDrawPile();
                break;
            case 1:
                manaNum = getManaNumInHand();
                break;
            case 2:
                manaNum = getManaNumInDiscardPile();
                break;
            default:
                manaNum = -1;
                Log.logger.info("NO SUCH SLOT NUMBER: {}", slotNumber);
                break;
        }
        return getChantChoices() != null && getChantChoices()[slotNumber] != null && (getManaNeed(chantX, getChantChoices()[slotNumber])) <= manaNum;
    }

    public static boolean cannotPlayLegendarySpell() {
        return getChantCardUsedThisTurn() == 0;
    }

    public static int getChantCardUsedThisTurn() {
        int amounts = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (c.hasTag(AbstractBaseCard.Enum.CHANT)) {
                amounts++;
            }
        }
        return amounts;
    }

    public static int getLegendarySpellUsedVarietyThisCombat(boolean isInUsingCard) {
        HashSet<String> set = new HashSet<>();
        int size = Math.max(0, (isInUsingCard ? AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1 : AbstractDungeon.actionManager.cardsPlayedThisCombat.size()));
        if (size == 0)
            return 0;
        for (int i = 0; i < size; i++) {
            AbstractCard c = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(i);
            if (c.hasTag(AbstractBaseCard.Enum.LEGENDARY_SPELL)) {
                set.add(c.cardID);
            }
        }
        return set.size();
    }

    public static int getCardsUsedThisTurnSize(boolean isInUsingCard) {
        int cardsPlayedThisTurnSize = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
        return isInUsingCard ? cardsPlayedThisTurnSize - 1 : cardsPlayedThisTurnSize;
    }

    public static int getContinualSynchroTimes(AbstractCard card) {
        ArrayList<AbstractCard> cardsPlayedThisTurn = AbstractDungeon.actionManager.cardsPlayedThisTurn;
        int counts = 0;
        for (int i = 0; i < cardsPlayedThisTurn.size(); i++) {
            if (cardsPlayedThisTurn.get(i).uuid.equals(card.uuid)) {
                for (int j = i; j >= 0; j--) {
                    if (cardsPlayedThisTurn.get(j) instanceof Mana) {
                        counts++;
                    } else
                        break;
                }
                break;
            }
        }
        return counts;
    }

    public static int getContinualSynchroMaxTimes() {
        int max = 0;
        ArrayList<AbstractCard> cardsPlayedThisTurn = AbstractDungeon.actionManager.cardsPlayedThisTurn;
        for (int i = 0; i < cardsPlayedThisTurn.size(); i++) {
            if (cardsPlayedThisTurn.get(i) instanceof Mana) {
                int counts = 0;
                for (int j = i; j < cardsPlayedThisTurn.size(); j++) {
                    if (cardsPlayedThisTurn.get(j) instanceof Mana)
                        counts++;
                    else
                        break;
                }
                if (counts > max)
                    max = counts;
            }
        }
        return max;
    }

    public static ConcentrationPower getConcentrationPower() {
        return (ConcentrationPower) AbstractDungeon.player.getPower(ConcentrationPower.POWER_ID);
    }

    public static int getWeakenedChantPowerAmt() {
        AbstractPower po = AbstractDungeon.player.getPower(WeakenedChantPower.POWER_ID);
        return po == null ? 0 : po.amount;
    }

    public static int getConcentrationPowerAmt() {
        AbstractPower po = AbstractDungeon.player.getPower(ConcentrationPower.POWER_ID);
        return po == null ? 0 : po.amount;
    }

    public static int getDeviationAmt(boolean isUsingCard) {
        return Math.abs(getCardsUsedThisTurnSize(isUsingCard) - getConcentrationPowerAmt());
    }

    public static boolean isDeviationEven(boolean isUsingCard) {
        return getDeviationAmt(isUsingCard) % 2 == 0;
    }

    public static boolean canRaidTakeEffect(int raidNumber, boolean isUsingCard) {
        return isRaidReversed() == (getDeviationAmt(isUsingCard) > raidNumber);
    }

    public static int getManaNeed(int chantX, AbstractMagicItem f) {
        int baseManaNeed = chantX * f.manaNeedMultipleCoefficient + f.manaNeedAddCoefficient;
        if (AbstractDungeon.player.hasPower(ChantWithoutManaPower.POWER_ID))
            return 0;
        if (AbstractDungeon.player.hasPower(ChantWithoutManaTimesPower.POWER_ID))
            return 0;
        return Math.max((baseManaNeed - getConcentrationPowerAmt() + getWeakenedChantPowerAmt()), 0);
    }

    public static boolean isRaidReversed() {
        return false;
    }

    public static AbstractMagicItem[] getChantChoices() {
        AbstractMagicItem[] chantChoices = new AbstractMagicItem[3];
        for (AbstractCard c : MagicDeckField.getDeck().group) {
            if (c instanceof AbstractMagicItem && ((AbstractMagicItem) c).currentSlot == 0) {
                chantChoices[0] = (AbstractMagicItem) c.makeStatEquivalentCopy();
            }
            if (c instanceof AbstractMagicItem && ((AbstractMagicItem) c).currentSlot == 1) {
                chantChoices[1] = (AbstractMagicItem) c.makeStatEquivalentCopy();
            }
            if (c instanceof AbstractMagicItem && ((AbstractMagicItem) c).currentSlot == 2) {
                chantChoices[2] = (AbstractMagicItem) c.makeStatEquivalentCopy();
            }
        }
        boolean isAllCopyFactor = true;
        for (AbstractCard c : chantChoices) {
            if (!(c instanceof BluePrint)) {
                isAllCopyFactor = false;
                break;
            }
        }
        if (isAllCopyFactor)
            return null;
        for (int i = 0; i < chantChoices.length; i++) {
            if (chantChoices[i] instanceof BluePrint) {
                int j = (i + 1) % chantChoices.length;
                while (chantChoices[j] instanceof BluePrint)
                    j = (j + 1) % chantChoices.length;
                AbstractMagicItem targetChantChoice = (AbstractMagicItem) chantChoices[j].makeStatEquivalentCopy();
                chantChoices[i] = targetChantChoice;
                chantChoices[i].currentSlot = i;
            }
        }
        return chantChoices;
    }

    public static AbstractMagicItem[] getLoadedMagicFactor() {
        AbstractMagicItem[] chantChoices = new AbstractMagicItem[3];
        for (AbstractCard c : MagicDeckField.getDeck().group) {
            if (c instanceof AbstractMagicItem && ((AbstractMagicItem) c).currentSlot == 0) {
                chantChoices[0] = (AbstractMagicItem) c;
            }
            if (c instanceof AbstractMagicItem && ((AbstractMagicItem) c).currentSlot == 1) {
                chantChoices[1] = (AbstractMagicItem) c;
            }
            if (c instanceof AbstractMagicItem && ((AbstractMagicItem) c).currentSlot == 2) {
                chantChoices[2] = (AbstractMagicItem) c;
            }
        }
        return chantChoices;
    }

    public static boolean isAllMagicFactorLoading() {
        for (AbstractMagicItem f : getLoadedMagicFactor()) {
            if (f == null)
                return false;
        }
        return true;
    }
}
