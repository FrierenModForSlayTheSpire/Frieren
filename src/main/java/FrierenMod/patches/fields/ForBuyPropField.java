package FrierenMod.patches.fields;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;

@SpirePatch(clz = GridCardSelectScreen.class, method = SpirePatch.CLASS)
public class ForBuyPropField {
    public static SpireField<Boolean> forBuyProp = new SpireField<>(() -> null);
}
