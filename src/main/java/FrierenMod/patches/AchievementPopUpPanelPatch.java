package FrierenMod.patches;

import FrierenMod.patches.fields.CardCrawlGameField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import java.util.ArrayList;

public class AchievementPopUpPanelPatch {
    @SpirePatch(clz = CardCrawlGame.class, method = "create")
    public static class PatchCreate {
        @SpirePostfixPatch
        public static void Postfix(CardCrawlGame __instance) {
            CardCrawlGameField.achievementPopUpPanelQueue.set(new ArrayList<>());
        }
    }
}
