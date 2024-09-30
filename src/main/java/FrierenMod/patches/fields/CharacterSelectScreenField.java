package FrierenMod.patches.fields;

import FrierenMod.ui.screens.SlotBgLibrary;
import FrierenMod.ui.slot.Slot;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;

import java.util.ArrayList;

@SpirePatch(clz = CharacterSelectScreen.class, method = SpirePatch.CLASS)
public class CharacterSelectScreenField {
    public static SpireField<ArrayList<Slot>> slots = new SpireField<>(ArrayList::new);
    public static SpireField<SlotBgLibrary> slotBgLibrary = new SpireField<>(SlotBgLibrary::new);
}

