package FrierenMod.patches;

import FrierenMod.patches.fields.ForBuyPropField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;

@SpirePatch(clz = GridCardSelectScreen.class, method = "callOnOpen")
public class PatchGridCardSelectScreen {
    @SpirePrefixPatch
    public static void Prefix(GridCardSelectScreen __instance) {
        ForBuyPropField.forBuyProp.set(__instance, false);
    }
}
