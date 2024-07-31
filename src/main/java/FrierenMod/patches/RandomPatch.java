package FrierenMod.patches;

import FrierenMod.gameHelpers.SaveFileHelper;
import FrierenMod.patches.fields.RandomField;
import FrierenMod.patches.fields.RandomField2;
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
            RandomField.magicItemRandomRng.set(new Random(Settings.seed));
        }
    }

    @SpirePatch(clz = AbstractDungeon.class, method = "loadSeeds")
    public static class PatchLoadSeeds {
        @SpirePostfixPatch
        public static void Postfix() {
            RandomField.magicItemRng.set(new Random(Settings.seed, SaveFileHelper.getMagicItemRngCount()));
            Log.logger.info("Magic Item Seed:{}", RandomField.getMagicItemRng().counter);
            RandomField.magicItemRandomizer.set(SaveFileHelper.getMagicItemRandomizer());
            Log.logger.info("Magic Item Randomizer:{}", RandomField.magicItemRandomizer.get());
            RandomField.magicItemRandomRng.set(new Random(Settings.seed, SaveFileHelper.getMagicItemRandomRngCount()));
            Log.logger.info("Magic Item Seed:{}", RandomField.getMagicItemRandomRng().random);
        }
    }

    @SpirePatch(clz = AbstractDungeon.class, method = "reset")
    public static class PatchReset {
        @SpirePostfixPatch
        public static void Postfix() {
            RandomField.magicItemRandomizer.set(RandomField.magicItemBlizzStartOffset);
        }
    }

    @SpirePatch(clz = AbstractDungeon.class, method = "loadSave")
    public static class PatchLoadSave {
        @SpirePostfixPatch
        public static void Postfix() {
            RandomField2.blizzardMagicItemMod.set(SaveFileHelper.getMagicItemChance());
        }
    }
}
