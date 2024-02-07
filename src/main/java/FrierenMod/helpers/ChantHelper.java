package FrierenMod.helpers;

import FrierenMod.cards.white.chant.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static FrierenMod.tags.CustomTags.MAGIC_POWER;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;

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
    private ArrayList<AbstractCard> initChantCardPool(){
        ArrayList<AbstractCard> pool = new ArrayList<>();
        pool.add(new DefendMagic());
        pool.add(new HolyChant());
        pool.add(new ContinualChant());
        pool.add(new FlowerFieldMagic());
        pool.add(new StatueMagic());
        pool.add(new CompleteDefendMagic());
        return pool;
    }
    public AbstractCard getRandomCard(){
        ArrayList<AbstractCard> list = initChantCardPool();
        return (AbstractCard)list.get(cardRandomRng.random(list.size() - 1));
    }

}
