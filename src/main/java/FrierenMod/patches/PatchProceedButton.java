package FrierenMod.patches;

import FrierenMod.enums.CharacterEnums;
import FrierenMod.utils.Config;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.buttons.ProceedButton;

import java.util.ArrayList;
import java.util.Collections;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.monsterRng;

@SpirePatch(clz = ProceedButton.class, method = "goToDoubleBoss")
public class PatchProceedButton {
    @SpireInsertPatch(rloc = 1)
    public static void Insert(ProceedButton __inst) {
        if (Config.REPLACE_CORRUPT_HEART && AbstractDungeon.player.chosenClass == CharacterEnums.FRIEREN) {
            AbstractDungeon.bossKey = getOriginalBoss();
        }
    }

    private static String getOriginalBoss() {
        ArrayList<String> bossList = new ArrayList<>();
        bossList.add("Awakened One");
        bossList.add("Donu and Deca");
        Collections.shuffle(bossList, new java.util.Random(monsterRng.randomLong()));
        return bossList.get(0);
    }
}
