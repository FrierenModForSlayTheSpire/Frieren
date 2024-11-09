package FrierenMod.patches.fields;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.StaticSpireField;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(clz = GameActionManager.class, method = SpirePatch.CLASS)
public class GameActionManagerField {
    public static SpireField<Integer> raidTriggeredThisTurn = new StaticSpireField<>(() -> 0);
    public static SpireField<Integer> raidTriggeredThisCombat = new StaticSpireField<>(() -> 0);

    public static int getRaidTriggeredThisTurn() {
        return raidTriggeredThisTurn.get(AbstractDungeon.actionManager);
    }

    public static int getRaidTriggeredThisCombat() {
        return raidTriggeredThisCombat.get(AbstractDungeon.actionManager);
    }
}
