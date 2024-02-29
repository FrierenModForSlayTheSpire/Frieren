package FrierenMod.gameHelpers;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.white.FlyingMagic;
import FrierenMod.cards.white.AccessoriesSpell;
import FrierenMod.cards.white.OilSpell;
import FrierenMod.cards.white.LightningMagic;
import FrierenMod.cards.white.chant.ContinualChant;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;

public class LegendMagicHelper {
    public static boolean cannotPlayLegendMagic(){

        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (c instanceof AbstractFrierenCard && ((AbstractFrierenCard)c).isChantCard) {
                return false;
            }
        }
        return true;
    }
    public static int getExhaustedMagicPowerNumber(){
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isMana) {
                counts++;
            }
        }
        return counts;
    }

    private static ArrayList<AbstractCard> initLegendMagicCardPool(){
        ArrayList<AbstractCard> pool = new ArrayList<>();
        pool.add(new FlyingMagic());
        pool.add(new ContinualChant());
        pool.add(new AccessoriesSpell());
        pool.add(new OilSpell());
        pool.add(new LightningMagic());
        return pool;
    }
    public static AbstractCard getRandomCard(){
        ArrayList<AbstractCard> list = initLegendMagicCardPool();
        return (AbstractCard)list.get(cardRandomRng.random(list.size() - 1));
    }
    public static boolean canLegendMagicUse(AbstractCard c, AbstractMonster m){
        if (cannotPlayLegendMagic()){
            return false;
        }
        else {
            return c.cardPlayable(m) && c.hasEnoughEnergy();
        }
    }
}
