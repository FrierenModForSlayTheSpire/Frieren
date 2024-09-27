package FrierenMod.patches.fields;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.google.gson.annotations.SerializedName;
import com.megacrit.cardcrawl.screens.stats.RunData;

import java.util.List;

@SpirePatch(clz = RunData.class, method = "<class>")
public class Magic_DeckField {
    @SerializedName("magic_deck")
    public static SpireField<List<String>> magic_deck = new SpireField<>(() -> null);
    @SerializedName("loaded_factors")
    public static SpireField<List<String>> loaded_factors = new SpireField<>(() -> null);
}
