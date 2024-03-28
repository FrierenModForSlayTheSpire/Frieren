package FrierenMod.patches;

import FrierenMod.cards.tempCards.Mana;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

@SpirePatch(clz = AbstractDungeon.class, method = "getRewardCards")
public class PatchGetRewardCards {
    @SpireInsertPatch(rloc = 74,localvars = {"retVal2"})
    public static void Insert(AbstractDungeon _inst, ArrayList<AbstractCard> retVal2) {
//        if (AbstractDungeon.player.hasRelic(Sakura.ID))
            for (AbstractCard c : retVal2) {
                int rng = AbstractDungeon.cardRng.random(100);
                if (rng <= 70) {
                    retVal2.remove(c);
                    retVal2.add(new Mana());
                }
            }
    }
}