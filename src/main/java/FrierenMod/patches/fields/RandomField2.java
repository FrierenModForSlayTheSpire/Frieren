package FrierenMod.patches.fields;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.StaticSpireField;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

@SpirePatch(clz = AbstractRoom.class, method = SpirePatch.CLASS)
public class RandomField2 {
    public static StaticSpireField<Integer> blizzardMagicItemMod = new StaticSpireField<>(() -> 0);

    public static Integer getBlizzardMagicItemMod() {
        return blizzardMagicItemMod.get();
    }

    public static void addBlizzardMagicItemMod(Integer value) {
        blizzardMagicItemMod.set(blizzardMagicItemMod.get() + value);
    }
}
