package FrierenMod.helpers;

import FrierenMod.cards.white.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Collections;

import static FrierenMod.tags.CustomTags.MAGIC_POWER;

public class ChantHelper {



    public int getMagicPowerNumInDrawPile(){
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.tags.contains(MAGIC_POWER)) {
                counts++;
            }
        }
        return counts;
    }
    public int getMagicPowerNumInHand(){
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.tags.contains(MAGIC_POWER)) {
                counts++;
            }
        }
        return counts;
    }
    public int getMagicPowerNumInDiscardPile(){
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.tags.contains(MAGIC_POWER)) {
                counts++;
            }
        }
        return counts;
    }
    public int getAllMagicPowerNum(){
        return this.getMagicPowerNumInDrawPile() + this.getMagicPowerNumInHand() + this.getMagicPowerNumInDiscardPile();
    }
    public boolean cannotChant(int x){
        return !this.canChantFromDrawPile(x) && !this.canChantFromHand(x) && !this.canChantFromDiscardPile(x);
    }
    public boolean canChantFromDrawPile(int x){
        int num = this.getMagicPowerNumInDrawPile();
        return x <= num;
    }
    public boolean canChantFromHand(int x){
        int num = this.getMagicPowerNumInHand();
        return x <= num;
    }
    public boolean canChantFromDiscardPile(int x){
        int num = this.getMagicPowerNumInDiscardPile();
        return x <= num;
    }
    public boolean canChantUse(AbstractCard c, AbstractMonster m, int x){
        if (c.type == AbstractCard.CardType.STATUS && c.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Medical Kit")) {
            return false;
        } else if (c.type == AbstractCard.CardType.CURSE && c.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Blue Candle")) {
            return false;
        } else if (this.cannotChant(x)){
            return false;
        }
        else {
            return c.cardPlayable(m) && c.hasEnoughEnergy();
        }
    }
    private ArrayList<AbstractCard> intiChantCardPool(){
        ArrayList<AbstractCard> pool = new ArrayList<>();
        pool.add(new FangYuMoFa());
        pool.add(new HolyChant());
        pool.add(new LianHuanYongChang());
        pool.add(new HuaTianMoFa());
        return pool;
    }
    public ArrayList<AbstractCard> getRandomCards(int amounts){
        ArrayList<AbstractCard> pool = this.intiChantCardPool();
        if(pool.size() >= amounts){
            Collections.shuffle(pool);
            ArrayList<AbstractCard> cards = new ArrayList<>();
            for (int i = 0; i < amounts; i++) {
                cards.add(pool.get(i));
            }
            return cards;
        }
        else {
            return null;
        }
    }

}
