package FrierenMod.patches;

import FrierenMod.monsters.Spiegel_Frieren;
import FrierenMod.utils.Config;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheEnding;

import java.util.ArrayList;

@SpirePatch(clz = TheEnding.class, method = "initializeBoss")
public class TheEndingPatch {
    @SpireInsertPatch(rloc = 0, localvars = {"bossList"})
    public static SpireReturn<Void> Insert(AbstractDungeon _inst, ArrayList<String> bossList){
        if(Config.REPLACE_CORRUPT_HEART){
            bossList.add(Spiegel_Frieren.MONSTER_ID);
            bossList.add(Spiegel_Frieren.MONSTER_ID);
            bossList.add(Spiegel_Frieren.MONSTER_ID);
            return SpireReturn.Return();
        }else {
            return SpireReturn.Continue();
        }
    }
}
