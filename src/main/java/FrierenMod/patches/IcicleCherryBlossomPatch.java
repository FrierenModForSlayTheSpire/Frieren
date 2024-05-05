package FrierenMod.patches;

import FrierenMod.cards.tempCards.Mana;
import FrierenMod.relics.IcicleCherryBlossom;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class IcicleCherryBlossomPatch {
    @SpirePatch(clz = AbstractDungeon.class, method = "getColorlessRewardCards")
    public static class PatchGetColorlessRewardCards {
        @SpirePostfixPatch
        public static ArrayList<AbstractCard> ChangeCards(ArrayList<AbstractCard> retVal2) {
            if (AbstractDungeon.player.hasRelic(IcicleCherryBlossom.ID))
                for (int i = 0; i < retVal2.size(); i++) {
                    int rng = AbstractDungeon.cardRng.random(100);
                    if (rng <= IcicleCherryBlossom.RATE) {
                        retVal2.set(i, new Mana());
                    }
                }
            return retVal2;
        }
    }
    @SpirePatch(clz = AbstractDungeon.class, method = "getRewardCards")
    public static class PatchGetRewardCards {
        @SpirePostfixPatch
        public static ArrayList<AbstractCard> ChangeCards(ArrayList<AbstractCard> retVal2) {
            if (AbstractDungeon.player.hasRelic(IcicleCherryBlossom.ID))
                for (int i = 0; i < retVal2.size(); i++) {
                    int rng = AbstractDungeon.cardRng.random(100);
                    if (rng <= IcicleCherryBlossom.RATE) {
                        retVal2.set(i, new Mana());
                    }
                }
            return retVal2;
        }
    }
}
