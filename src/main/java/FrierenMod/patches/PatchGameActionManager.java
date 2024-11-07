package FrierenMod.patches;

import FrierenMod.patches.fields.GameActionManagerField;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.GameActionManager;

public class PatchGameActionManager {
    @SpirePatch(clz = GameActionManager.class, method = "getNextAction")
    public static class PatchGetNextAction {
        @SpireInsertPatch(rloc = 456 - 210)
        public static void Insert(GameActionManager __instance) {
            GameActionManagerField.raidTriggeredThisTurn.set(__instance, 0);
        }
    }

    @SpirePatch(clz = GameActionManager.class, method = "clear")
    public static class PatchClear {
        @SpirePostfixPatch
        public static void Insert(GameActionManager __instance) {
            GameActionManagerField.raidTriggeredThisTurn.set(__instance, 0);
            GameActionManagerField.raidTriggeredThisCombat.set(__instance, 0);
        }
    }
}
