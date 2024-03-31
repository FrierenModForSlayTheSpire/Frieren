package FrierenMod.gameHelpers;

import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.cards.canAutoAdd.white.*;
import FrierenMod.cards.whitePurple.RapidChant;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;

public class ChantHelper {
    public static int getManaNumInDrawPile(){
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c instanceof AbstractMagicianCard && ((AbstractMagicianCard) c).isMana) {
                counts++;
            }
        }
        return counts;
    }
    public static int getManaNumInHand(){
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof AbstractMagicianCard && ((AbstractMagicianCard) c).isMana) {
                counts++;
            }
        }
        return counts;
    }
    public static int getManaNumInDiscardPile(){
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c instanceof AbstractMagicianCard && ((AbstractMagicianCard) c).isMana) {
                counts++;
            }
        }
        return counts;
    }
    public static int getAllManaNum(){
        return getManaNumInDrawPile() + getManaNumInHand() + getManaNumInDiscardPile();
    }
    public static boolean cannotChant(int x){
        return !canChantFromDrawPile(x) && !canChantFromHand(x) && !canChantFromDiscardPile(x);
    }
    public static boolean canChantFromDrawPile(int x){
        int num = getManaNumInDrawPile();
        return x <= num;
    }
    public static boolean canChantFromHand(int x){
        int num = getManaNumInHand();
        return x <= num;
    }
    public static boolean canChantFromDiscardPile(int x){
        int num = getManaNumInDiscardPile();
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
        pool.add(new LureTheEnemyInDeep());
        return pool;
    }
    public static AbstractCard getRandomCard(){
        ArrayList<AbstractCard> list = initChantCardPool();
        return list.get(cardRandomRng.random(list.size() - 1));
    }

}
