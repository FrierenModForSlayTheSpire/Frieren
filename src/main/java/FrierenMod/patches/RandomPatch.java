package FrierenMod.patches;

import FrierenMod.gameHelpers.MagicItemHelper;
import FrierenMod.patches.fields.RandomField;
import FrierenMod.utils.Log;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.random.Random;


public class RandomPatch {
    @SpirePatch(clz = AbstractDungeon.class, method = "generateSeeds")
    public static class PatchGenerateSeeds {
        @SpirePostfixPatch
        public static void Postfix() {
            RandomField.magicItemRng.set(new Random(Settings.seed));
        }
    }

    @SpirePatch(clz = AbstractDungeon.class, method = "loadSeeds")
    public static class PatchLoadSeeds {
        @SpirePostfixPatch
        public static void Postfix() {
            RandomField.magicItemRng.set(new Random(Settings.seed, MagicItemHelper.getMagicItemRngCount()));
            Log.logger.info("Magic Item Seed:{}", RandomField.getMagicItemRng().counter);
        }
    }
}
