package FrierenMod.helpers;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.white.FlightMagic;
import FrierenMod.cards.white.JewelryMagic;
import FrierenMod.cards.white.OilMagic;
import FrierenMod.cards.white.chant.ContinualChant;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static FrierenMod.tags.CustomTags.MAGIC_POWER;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;

public class LegendMagicHelper {


    public boolean cannotPlayLegendMagic(){

        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (c instanceof AbstractFrierenCard && ((AbstractFrierenCard)c).isChantCard) {
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
    public AbstractCard getRandomCard(){
        ArrayList<AbstractCard> list = initLegendMagicCardPool();
        return (AbstractCard)list.get(cardRandomRng.random(list.size() - 1));
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
