package FrierenMod.gameHelpers;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.optionCards.magicItems.AbstractMagicItem;
import FrierenMod.patches.fields.MagicDeckField;
import FrierenMod.powers.ChantWithoutManaPower;
import FrierenMod.powers.ConcentrationPower;
import FrierenMod.powers.WeakenedChantPower;
import FrierenMod.utils.Log;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.HashSet;

public class CombatHelper {
    public static boolean isInCombat() {
        return (AbstractDungeon.getCurrMapNode() != null && AbstractDungeon.getCurrRoom() != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT);
    }

    public static int getManaNumInDrawPile() {
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c instanceof AbstractBaseCard && ((AbstractBaseCard) c).isMana) {
                counts++;
            }
        }
        return counts;
    }

    public static int getManaNumInHand() {
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof AbstractBaseCard && ((AbstractBaseCard) c).isMana) {
                counts++;
            }
        }
        return counts;
    }

    public static int getManaNumInDiscardPile() {
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c instanceof AbstractBaseCard && ((AbstractBaseCard) c).isMana) {
                counts++;
            }
        }
        return counts;
    }

    public static int getManaNumInExhaustPile() {
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c instanceof AbstractBaseCard && ((AbstractBaseCard) c).isMana) {
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
        return getChantChoices()[slotNumber] != null && (getManaNeed(chantX, getChantChoices()[slotNumber])) <= manaNum;
    }

    public static boolean cannotPlayLegendarySpell() {
        return getChantCardUsedThisTurn() == 0;
    }

    public static int getChantCardUsedThisTurn() {
        int amounts = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (c instanceof AbstractBaseCard && ((AbstractBaseCard) c).isChantCard) {
                amounts++;
            }
        }
        return amounts;
    }

    public static int getLegendarySpellUsedVarietyThisCombat(boolean isInUsingCard) {
        int amounts;
        HashSet<String> set = new HashSet<>();
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c instanceof AbstractBaseCard && ((AbstractBaseCard) c).isLegendarySpell) {
                set.add(c.cardID);
            }
        }
        amounts = set.size();
        if (isInUsingCard)
            amounts--;
        return amounts;
    }

    public static int getCardsUsedThisTurnSize(boolean isInUsingCard) {
        int cardsPlayedThisTurnSize = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
        return isInUsingCard ? cardsPlayedThisTurnSize - 1 : cardsPlayedThisTurnSize;
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
        return AbstractDungeon.player.hasPower(ChantWithoutManaPower.POWER_ID) ? 0 : Math.max((baseManaNeed - getConcentrationPowerAmt() + getWeakenedChantPowerAmt()), 0);
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
        return chantChoices;
    }
}
