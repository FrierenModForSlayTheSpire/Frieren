package FrierenMod.patches.fields;

import FrierenMod.ui.slot.Slot;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;

import java.util.ArrayList;

@SpirePatch(clz = CardRewardScreen.class, method = SpirePatch.CLASS)
public class CardRewardScreenField {
    public static SpireField<ArrayList<Slot>> slots = new SpireField<>(ArrayList::new);

    public static ArrayList<Slot> getSlots() {
        return slots.get(AbstractDungeon.cardRewardScreen);
    }
}
