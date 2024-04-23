package FrierenMod.patches;

import FrierenMod.enums.CharacterEnums;
import FrierenMod.utils.Config;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.DeadBranch;
import com.megacrit.cardcrawl.relics.StrangeSpoon;
import com.megacrit.cardcrawl.relics.VelvetChoker;

@SpirePatch(clz = AbstractDungeon.class, method = "initializeRelicList")
public class PatchReturnRandomRelicKey {
    @SpirePostfixPatch
    public static void Post() {
        if (AbstractDungeon.player.chosenClass == CharacterEnums.FRIEREN) {
            if (Config.REMOVE_VELVET_CHOKER)
                AbstractDungeon.bossRelicPool.removeIf(key -> key.equals(VelvetChoker.ID));
            if (Config.REMOVE_DEAD_BRANCH)
                AbstractDungeon.rareRelicPool.removeIf(key -> key.equals(DeadBranch.ID));
            if (Config.REMOVE_STRANGE_SPOON)
                AbstractDungeon.shopRelicPool.removeIf(key -> key.equals(StrangeSpoon.ID));
        }
    }
}
