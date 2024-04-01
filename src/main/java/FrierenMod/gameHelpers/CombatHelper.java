package FrierenMod.gameHelpers;

import FrierenMod.cards.AbstractBaseCard;
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

    public static boolean cannotChant(int x) {
        return !canChantFromDrawPile(x) && !canChantFromHand(x) && !canChantFromDiscardPile(x);
    }

    public static boolean canChantFromDrawPile(int x) {
        return x <= getManaNumInDrawPile();
    }

    public static boolean canChantFromHand(int x) {
        return x <= getManaNumInHand();
    }

    public static boolean canChantFromDiscardPile(int x) {
        return x <= getManaNumInDiscardPile();
    }

    public static boolean canChantUse(AbstractCard c, AbstractMonster m, int x) {
        if (cannotChant(x)) {
            return false;
        } else {
            return c.cardPlayable(m) && c.hasEnoughEnergy();
        }
    }

    public static boolean cannotPlayLegendarySpell() {
        return getChantCardUsedThisTurn() == 0;
    }

    public static boolean canLegendarySpellUse(AbstractCard c, AbstractMonster m) {
        if (cannotPlayLegendarySpell()) {
            return false;
        } else {
            return c.cardPlayable(m) && c.hasEnoughEnergy();
        }
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
        return isInUsingCard ? cardsPlayedThisTurnSize - 1: cardsPlayedThisTurnSize;
    }

    public static int getConcentrationPowerAmt() {
        AbstractPower po = AbstractDungeon.player.getPower(HardCodedPowerHelper.CONCENTRATION);
        return po == null ? 0 : po.amount;
    }

    public static int getDeviationAmt(boolean isUsingCard) {
        return Math.abs(getCardsUsedThisTurnSize(isUsingCard) - getConcentrationPowerAmt());
    }
    public static boolean isDeviationEven(boolean isUsingCard){
        return getDeviationAmt(isUsingCard) % 2 == 0;
    }
}
