package FrierenMod.gameHelpers;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.white.chant.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;

public class ChantHelper {
    public static int getMagicPowerNumInDrawPile(){
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isMana) {
                counts++;
            }
        }
        return counts;
    }
    public static int getMagicPowerNumInHand(){
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isMana) {
                counts++;
            }
        }
        return counts;
    }
    public static int getMagicPowerNumInDiscardPile(){
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isMana) {
                counts++;
            }
        }
        return counts;
    }
    public static int getAllMagicPowerNum(){
        return getMagicPowerNumInDrawPile() + getMagicPowerNumInHand() + getMagicPowerNumInDiscardPile();
    }
    public static boolean cannotChant(int x){
        return !canChantFromDrawPile(x) && !canChantFromHand(x) && !canChantFromDiscardPile(x);
    }
    public static boolean canChantFromDrawPile(int x){
        int num = getMagicPowerNumInDrawPile();
        return x <= num;
    }
    public static boolean canChantFromHand(int x){
        int num = getMagicPowerNumInHand();
        return x <= num;
    }
    public static boolean canChantFromDiscardPile(int x){
        int num = getMagicPowerNumInDiscardPile();
        return x <= num;
    }
    public static boolean canChantUse(AbstractCard c, AbstractMonster m, int x){
        if (cannotChant(x)){
            return false;
        }
        else {
            return c.cardPlayable(m) && c.hasEnoughEnergy();
        }
    }
    private static ArrayList<AbstractCard> initChantCardPool(){
        ArrayList<AbstractCard> pool = new ArrayList<>();
        pool.add(new DefensiveMagic());
        pool.add(new RapidChant());
        pool.add(new ContinualChant());
        pool.add(new FlowerFieldSpell());
        pool.add(new RustCleanMagic());
        pool.add(new PerfectDefensiveMagic());
        pool.add(new FinalChant());
        pool.add(new PreciseChant());
        pool.add(new TrueColours());
        return pool;
    }
    public static AbstractCard getRandomCard(){
        ArrayList<AbstractCard> list = initChantCardPool();
        return list.get(cardRandomRng.random(list.size() - 1));
    }

}
