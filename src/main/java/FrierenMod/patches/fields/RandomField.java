package FrierenMod.patches.fields;

import com.evacipated.cardcrawl.modthespire.lib.StaticSpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.random.Random;

@SpirePatch(clz = AbstractDungeon.class, method = SpirePatch.CLASS)
public class RandomField {
    public static final int magicItemBlizzStartOffset = 5;
    public static int magicItemBlizzGrowth = 1;

    public static int magicItemBlizzMaxOffset = -40;
    public static StaticSpireField<Random> magicItemRng = new StaticSpireField<>(() -> null);
    public static StaticSpireField<Integer> magicItemRandomizer = new StaticSpireField<>(() -> AbstractDungeon.cardBlizzStartOffset);
    public static StaticSpireField<Random> magicItemRandomRng = new StaticSpireField<>(() -> null);

    public static Random getMagicItemRng() {
        return magicItemRng.get();
    }

    public static Integer getMagicItemRandomizer() {
        return magicItemRandomizer.get();
    }

    public static void addMagicItemRandomizer(Integer value) {
        magicItemRandomizer.set(magicItemRandomizer.get() + value);
    }

    public static Random getMagicItemRandomRng() {
        return magicItemRandomRng.get();
    }
}
