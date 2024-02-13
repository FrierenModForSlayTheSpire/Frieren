package FrierenMod.gameHelpers;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.white.FlightMagic;
import FrierenMod.cards.white.FindLostOrnamentMagic;
import FrierenMod.cards.white.OilMagic;
import FrierenMod.cards.white.ThunderMagic;
import FrierenMod.cards.white.chant.ContinualChant;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

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
            if (c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isMagicPower) {
                counts++;
            }
        }
        return counts;
    }

    private ArrayList<AbstractCard> initLegendMagicCardPool(){
        ArrayList<AbstractCard> pool = new ArrayList<>();
        pool.add(new FlightMagic());
        pool.add(new ContinualChant());
        pool.add(new FindLostOrnamentMagic());
        pool.add(new OilMagic());
        pool.add(new ThunderMagic());
        return pool;
    }
    public AbstractCard getRandomCard(){
        ArrayList<AbstractCard> list = initLegendMagicCardPool();
        return (AbstractCard)list.get(cardRandomRng.random(list.size() - 1));
    }
    public boolean canLegendMagicUse(AbstractCard c, AbstractMonster m){
        if (this.cannotPlayLegendMagic()){
            return false;
        }
        else {
            return c.cardPlayable(m) && c.hasEnoughEnergy();
        }
    }
}
