package FrierenMod.patches;

import FrierenMod.enums.CharacterEnums;
import FrierenMod.gameHelpers.SlotBgHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;

public class AchievementPatch {
    @SpirePatch(clz = CorruptHeart.class, method = "die")
    public static class PatchDie {
        @SpireInsertPatch(rloc = 5)
        public static void Insert() {
            if (AbstractDungeon.player.chosenClass == CharacterEnums.FRIEREN) {
                String newSlotId = SlotBgHelper.rollANewCommonSlotId(AbstractDungeon.ascensionLevel);
                if (newSlotId != null)
                    SlotBgHelper.unlockANewSlot(newSlotId);
            }
        }
    }
}
