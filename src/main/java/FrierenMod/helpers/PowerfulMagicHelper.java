package FrierenMod.helpers;

import FrierenMod.cards.white.powerfulMagic.FeiXingMoFa;
import FrierenMod.cards.white.HeiAnYongChang;
import FrierenMod.cards.white.powerfulMagic.LianHuanYongChang;
import FrierenMod.cards.white.powerfulMagic.ShaRenMoFa;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Collections;

import static FrierenMod.tags.CustomTags.*;

public class PowerfulMagicHelper {


    public boolean cannotPlayPowerfulMagic(){

        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (c.hasTag(CHANT)) {
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
        return Math.min(counts, 20);
    }

    private ArrayList<AbstractCard> initPowerMagicCardPool(){
        ArrayList<AbstractCard> pool = new ArrayList<>();
        pool.add(new FeiXingMoFa());
        pool.add(new ShaRenMoFa());
        pool.add(new LianHuanYongChang());
        return pool;
    }
    public ArrayList<AbstractCard> getRandomCards(int amounts){
        ArrayList<AbstractCard> pool = this.initPowerMagicCardPool();
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
    public boolean canPowerfulMagicUse(AbstractCard c, AbstractPlayer p, AbstractMonster m){
        if (c.type == AbstractCard.CardType.STATUS && c.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Medical Kit")) {
            return false;
        } else if (c.type == AbstractCard.CardType.CURSE && c.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Blue Candle")) {
            return false;
        } else if (this.cannotPlayPowerfulMagic()){
            return false;
        }
        else {
            return c.cardPlayable(m) && c.hasEnoughEnergy();
        }

    }
}
