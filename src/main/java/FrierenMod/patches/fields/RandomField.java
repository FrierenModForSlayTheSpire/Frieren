package FrierenMod.patches.fields;

import com.evacipated.cardcrawl.modthespire.lib.StaticSpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.random.Random;

@SpirePatch(clz = AbstractDungeon.class, method = SpirePatch.CLASS)
public class RandomField {
    public static StaticSpireField<Random> magicItemRng = new StaticSpireField<>(() -> null);
    public static Random getMagicItemRng() {
        return magicItemRng.get();
    }
}
