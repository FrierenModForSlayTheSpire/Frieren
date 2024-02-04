package FrierenMod.helpers;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.white.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Collections;

import static FrierenMod.tags.CustomTags.*;

public class LegendMagicHelper {


    public boolean cannotPlayLegendMagic(){

        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (((AbstractFrierenCard)c).isChantCard) {
                return false;
            }
        }
        return true;
    }
    public int getExhaustedMagicPowerNumber(){
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c.hasTag(MAGIC_POWER)) {
                counts++;
            }
        }
        return counts;
    }

    private ArrayList<AbstractCard> initLegendMagicCardPool(){
        ArrayList<AbstractCard> pool = new ArrayList<>();
        pool.add(new FlightMagic());
        pool.add(new ContinualChant());
        pool.add(new JewelryMagic());
        pool.add(new OilMagic());
        return pool;
    }
    public ArrayList<AbstractCard> getRandomCards(int amounts){
        ArrayList<AbstractCard> pool = this.initLegendMagicCardPool();
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
    public boolean canLegendMagicUse(AbstractCard c, AbstractMonster m){
        if (c.type == AbstractCard.CardType.STATUS && c.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Medical Kit")) {
            return false;
        } else if (c.type == AbstractCard.CardType.CURSE && c.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Blue Candle")) {
            return false;
        } else if (this.cannotPlayLegendMagic()){
            return false;
        }
        else {
            return c.cardPlayable(m) && c.hasEnoughEnergy();
        }

    }
}
