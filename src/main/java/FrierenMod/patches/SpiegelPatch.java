package FrierenMod.patches;

import FrierenMod.enums.CharacterEnums;
import FrierenMod.monsters.Spiegel_Frieren;
import FrierenMod.utils.Config;
import basemod.BaseMod;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.ui.buttons.ProceedButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.monsterRng;

public class SpiegelPatch {
    @SpirePatch(clz = ProceedButton.class, method = "goToDoubleBoss")
    public static class PatchProceedButton {
        @SpireInsertPatch(rloc = 1)
        public static void Insert(ProceedButton __inst) {
            if (Config.ENCOUNTER_SPIEGEL && AbstractDungeon.player.chosenClass == CharacterEnums.FRIEREN) {
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

    @SpirePatch(clz = TheBeyond.class, method = "initializeBoss")
    public static class TheBeyondPatch {
        @SpireInsertPatch(rloc = 0, localvars = {"bossList"})
        public static SpireReturn<Void> Insert(AbstractDungeon _inst, ArrayList<String> bossList) {
            if (Config.ENCOUNTER_SPIEGEL && AbstractDungeon.player.chosenClass == CharacterEnums.FRIEREN) {
                bossList.add(Spiegel_Frieren.MONSTER_ID);
                bossList.add(Spiegel_Frieren.MONSTER_ID);
                bossList.add(Spiegel_Frieren.MONSTER_ID);
                return SpireReturn.Return();
            } else {
                if(AbstractDungeon.player.chosenClass != CharacterEnums.FRIEREN) {
                    HashMap<String, List<BaseMod.BossInfo>> customBosses = ReflectionHacks.getPrivateStatic(BaseMod.class,"customBosses");
                    List<BaseMod.BossInfo> bossInfoList = customBosses.get(TheBeyond.ID);
                    bossInfoList.removeIf(bossInfo -> bossInfo.id.equals(Spiegel_Frieren.MONSTER_ID));
                }
                return SpireReturn.Continue();
            }
        }
    }
}
