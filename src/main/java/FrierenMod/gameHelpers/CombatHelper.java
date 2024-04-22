package FrierenMod.gameHelpers;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.powers.ChantWithoutManaPower;
import FrierenMod.powers.ConcentrationPower;
import FrierenMod.powers.WeakenedChantPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

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

    public static boolean cannotChant(int manaNeed) {
        return !canChantFromDrawPile(manaNeed) && !canChantFromHand(manaNeed) && !canChantFromDiscardPile(manaNeed);
    }

    public static boolean canChantFromDrawPile(int manaNeed) {
        return manaNeed <= getManaNumInDrawPile();
    }

    public static boolean canChantFromHand(int manaNeed) {
        return manaNeed <= getManaNumInHand();
    }

    public static boolean canChantFromDiscardPile(int manaNeed) {
        return manaNeed <= getManaNumInDiscardPile();
    }

    public static boolean canChantUse(AbstractCard c, AbstractMonster m, int chantX) {
        return !cannotChant(getManaNeedWhenChant(chantX)) && c.cardPlayable(m) && c.hasEnoughEnergy();
    }

    public static boolean cannotPlayLegendarySpell() {
        return getChantCardUsedThisTurn() == 0;
    }

    public static boolean canLegendarySpellUse(AbstractCard c, AbstractMonster m) {
        return !cannotPlayLegendarySpell() && c.cardPlayable(m) && c.hasEnoughEnergy();
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

    public static int getManaNeedWhenChant(int chantX) {
        return AbstractDungeon.player.hasPower(ChantWithoutManaPower.POWER_ID) ? 0 : Math.max((chantX - getConcentrationPowerAmt() + getWeakenedChantPowerAmt()), 0);
    }

    public static boolean isRaidReversed() {
        return false;
    }
}
