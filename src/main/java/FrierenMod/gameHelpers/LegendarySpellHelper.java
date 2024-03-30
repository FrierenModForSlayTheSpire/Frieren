package FrierenMod.gameHelpers;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.white.*;
import FrierenMod.cards.white.chant.ContinualChant;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;

public class LegendarySpellHelper {
    public static boolean cannotPlayLegendarySpell(){
        return getChantCardUsedThisTurn() == 0;
    }
    public static int getExhaustedManaNumber(){
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isMana) {
                counts++;
            }
        }
        return counts;
    }

    private static ArrayList<AbstractCard> initLegendarySpellCardPool(){
        ArrayList<AbstractCard> pool = new ArrayList<>();
        pool.add(new FlyingMagic());
        pool.add(new ContinualChant());
        pool.add(new AccessoriesSpell());
        pool.add(new OilSpell());
        pool.add(new LightningMagic());
        pool.add(new Famehameha());
        return pool;
    }
    public static AbstractCard getRandomCard(){
        ArrayList<AbstractCard> list = initLegendarySpellCardPool();
        return (AbstractCard)list.get(cardRandomRng.random(list.size() - 1));
    }
    public static boolean canLegendarySpellUse(AbstractCard c, AbstractMonster m){
        if (cannotPlayLegendarySpell()){
            return false;
        }
        else {
            return c.cardPlayable(m) && c.hasEnoughEnergy();
        }
    }
    public static int getChantCardUsedThisTurn(){
        int amounts = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (c instanceof AbstractFrierenCard && ((AbstractFrierenCard)c).isChantCard) {
                amounts++;
            }
        }
        return amounts;
    }
}
