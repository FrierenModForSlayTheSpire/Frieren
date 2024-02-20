package FrierenMod.patches;


import FrierenMod.Characters.Frieren;
import FrierenMod.FTUEUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.TipTracker;

public class TipTrackerPatch {
    @SpirePatch2(clz = TipTracker.class, method = "refresh")
    public static class DamageAfter {
        public static void Postfix() {
            TipTracker.tips.put(FTUEUtils.COMBAT_TIP_KEY, TipTracker.pref.getBoolean(FTUEUtils.COMBAT_TIP_KEY, false));
        }
    }

    @SpirePatch(clz = AbstractPlayer.class, method = "preBattlePrep")
    public static class CreateDrawPile {
        public static void Postfix(AbstractPlayer __instance) {
            if (__instance instanceof Frieren)
                FTUEUtils.openCombatTip();
        }
    }
}