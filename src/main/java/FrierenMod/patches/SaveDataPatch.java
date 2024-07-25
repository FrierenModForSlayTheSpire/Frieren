package FrierenMod.patches;

import FrierenMod.ModManager;
import FrierenMod.gameHelpers.DataObject;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.google.gson.annotations.SerializedName;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import javassist.CtBehavior;

import java.util.ArrayList;
import java.util.HashMap;


public class SaveDataPatch {
    public SaveDataPatch() {
    }

    @SpirePatch(
            clz = CardCrawlGame.class,
            method = "loadPlayerSave"
    )
    public static class Load {
        public Load() {
        }

        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"saveFile"}
        )
        public static void Insert(CardCrawlGame __instance, AbstractPlayer p, SaveFile saveFile) {
            ModManager.saveData = SaveField.saveData.get(saveFile);
        }

        private static class Locator extends SpireInsertLocator {
            private Locator() {
            }

            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(SaveFile.class, "hand_size");
                return LineFinder.findInOrder(ctBehavior, new ArrayList<>(), finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = SaveAndContinue.class,
            method = "save"
    )
    public static class Save {
        public Save() {
        }

        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"params"}
        )
        public static void Insert(SaveFile save, @ByRef HashMap<Object, Object>[] params) {
            params[0].put("frierenmod:save_data", SaveDataPatch.SaveField.saveData.get(save));
        }

        private static class Locator extends SpireInsertLocator {
            private Locator() {
            }

            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(SaveFile.class, "hand_size");
                return LineFinder.findInOrder(ctBehavior, new ArrayList<>(), finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = SaveFile.class,
            method = "<class>"
    )
    public static class SaveField {
        @SerializedName("frierenmod:save_data")
        public static SpireField<DataObject> saveData = new SpireField<>(() -> ModManager.saveData);

        public SaveField() {
        }
    }

}
