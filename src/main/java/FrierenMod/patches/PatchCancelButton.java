package FrierenMod.patches;

import FrierenMod.patches.fields.ForBuyPropField;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.ui.buttons.CancelButton;

@SpirePatch(clz = CancelButton.class, method = "update")
public class PatchCancelButton {
    @SpireInsertPatch(rloc = 44)
    public static SpireReturn<Void> Insert(CancelButton __instance) {
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID && ForBuyPropField.forBuyProp.get(AbstractDungeon.gridSelectScreen)) {
            AbstractDungeon.closeCurrentScreen();
            if (AbstractDungeon.getCurrRoom() instanceof RestRoom) {
                RestRoom r = (RestRoom) AbstractDungeon.getCurrRoom();
                r.campfireUI.reopen();
            }
            return SpireReturn.Return();
        }
        return SpireReturn.Continue();
    }
}
