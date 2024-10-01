package FrierenMod.patches.fields;

import FrierenMod.ui.panels.AchievementPopUpPanel;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.StaticSpireField;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import java.util.ArrayList;

@SpirePatch(clz = CardCrawlGame.class, method = SpirePatch.CLASS)
public class CardCrawlGameField {
    public static StaticSpireField<ArrayList<AchievementPopUpPanel>> achievementPopUpPanelQueue = new StaticSpireField<>(() -> null);
}
