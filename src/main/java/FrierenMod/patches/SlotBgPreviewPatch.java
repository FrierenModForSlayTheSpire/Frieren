package FrierenMod.patches;

import FrierenMod.enums.CharacterEnums;
import FrierenMod.gameHelpers.SlotBgHelper;
import FrierenMod.patches.fields.CharacterSelectScreenField;
import FrierenMod.ui.slot.Slot;
import FrierenMod.utils.ModInformation;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
import com.megacrit.cardcrawl.ui.panels.SeedPanel;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;

import java.util.ArrayList;

public class SlotBgPreviewPatch {
    private static final float startX = 1500.0F * Settings.scale;
    private static final float startY = Settings.HEIGHT - 100.0F * Settings.scale;
    private static final float padX = 100.0F * Settings.scale;
    private static final float padY = 100.0F * Settings.scale;

    @SpirePatch(clz = CharacterSelectScreen.class, method = "render")
    public static class PatchRender {
        private static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ModInformation.makeID("SlotBgPreview"))).TEXT;

        @SpireInsertPatch(rloc = 89, localvars = {"o"})
        public static void Insert(CharacterSelectScreen __instance, SpriteBatch sb, CharacterOption o) {
            if (o.c.chosenClass == CharacterEnums.FRIEREN && !isSeedPanelShown(__instance)) {
                renderBlackScreen(sb);
                FontHelper.renderFontLeft(sb, FontHelper.charDescFont, TEXT[0], startX, startY, Color.WHITE);
                ArrayList<Slot> slots = CharacterSelectScreenField.slots.get(__instance);
                if (slots != null && slots.size() == 3) {
                    for (Slot slot : slots) {
                        slot.render(sb);
                    }
                }
            }
        }

        private static void renderBlackScreen(SpriteBatch sb) {
            Color c = Color.BLACK;
            c.a = 0.5F;
            sb.setColor(c);
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, startX - 50.0F * Settings.scale, startY - 200.0F * Settings.scale, 3 * padX , 300.0F * Settings.scale);
        }
    }

    @SpirePatch(clz = CharacterSelectScreen.class, method = "render")
    public static class PatchRender2 {
        @SpirePostfixPatch
        public static void Postfix(CharacterSelectScreen __instance, SpriteBatch sb) {
            if (!isSeedPanelShown(__instance)) {
                CharacterSelectScreenField.slotBgLibrary.get(__instance).render(sb);
            }
        }
    }

    @SpirePatch(clz = CharacterSelectScreen.class, method = "render")
    public static class PatchRender3 {
        @SpireInstrumentPatch
        public static ExprEditor Instrument() {
            return new ExprEditor() {
                @Override
                public void edit(FieldAccess f) throws CannotCompileException {
                    if (isShownAccess(f)) {
                        f.replace("{" +
                                "if(this.seedPanel.shown ||" +
                                SlotBgPreviewPatch.class.getName() + ".cal(this)" +
                                ")" +
                                "{ $_ = true; }" +
                                "}");
                    }
                }
            };
        }
    }

    @SpirePatch(clz = CharacterSelectScreen.class, method = "initialize")
    public static class PatchInitialize {
        @SpirePostfixPatch
        public static void Postfix(CharacterSelectScreen __instance) {
            ArrayList<Slot> slots = SlotBgHelper.getLoadingSlotsInPreview();
            CharacterSelectScreenField.slots.set(__instance, slots);
            for (int i = 0; i < slots.size(); i++) {
                slots.get(i).setPosition(startX + i * padX, startY - padY);
            }
        }
    }

    @SpirePatch(clz = CharacterSelectScreen.class, method = "update")
    public static class PatchUpdate {

        @SpireInsertPatch(rloc = 24, localvars = {"o"})
        public static void Insert(CharacterSelectScreen __instance, CharacterOption o) {
            if (o.c.chosenClass == CharacterEnums.FRIEREN) {
                ArrayList<Slot> slots = CharacterSelectScreenField.slots.get(__instance);
                for (Slot slot : slots) {
                    slot.update();
                    slot.updateHoverLogicInPreview(__instance, slots.indexOf(slot));
                }
            }
        }
    }

    @SpirePatch(clz = CharacterSelectScreen.class, method = "update")
    public static class PatchUpdate2 {

        @SpirePostfixPatch
        public static void Postfix(CharacterSelectScreen __instance) {
            CharacterSelectScreenField.slotBgLibrary.get(__instance).update();
        }
    }

    @SpirePatch(clz = CharacterSelectScreen.class, method = "update")
    public static class PatchUpdate3 {

        @SpireInstrumentPatch
        public static ExprEditor Instrument() {
            return new ExprEditor() {
                @Override
                public void edit(FieldAccess f) throws CannotCompileException {
                    if (isShownAccess(f)) {
                        f.replace("{" +
                                "if(this.seedPanel.shown ||" +
                                SlotBgPreviewPatch.class.getName() + ".cal(this)" +
                                ")" +
                                "{ $_ = true; }" +
                                "}");
                    }
                }
            };
        }
    }

    public static boolean isShownAccess(FieldAccess f) {
        String fieldName = f.getFieldName();
        String className = f.getClassName();
        return fieldName.equals("shown") && className.equals(SeedPanel.class.getName());
    }

    public static boolean cal(CharacterSelectScreen screen) {
        return CharacterSelectScreenField.slotBgLibrary.get(screen).shown;
    }

    public static boolean isSeedPanelShown(CharacterSelectScreen screen) {
        SeedPanel seedPanel = ReflectionHacks.getPrivate(screen, CharacterSelectScreen.class, "seedPanel");
        return seedPanel.shown;
    }

}
