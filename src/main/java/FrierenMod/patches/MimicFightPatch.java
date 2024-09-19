package FrierenMod.patches;

import FrierenMod.events.MimicFight;
import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.buttons.ProceedButton;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public class MimicFightPatch {
    @SpirePatch(clz = ProceedButton.class, method = "update")
    public static class PatchUpdate {
        private static boolean methodCalled = false;

        @SpireInstrumentPatch
        public static ExprEditor Instrument() {
            return new ExprEditor() {
                @Override
                public void edit(MethodCall m) throws CannotCompileException {
                    if (isMethodCalled(m)) {
                        m.replace("{" +
                                "if (" + MimicFightPatch.PatchUpdate.class.getName() + ".check()) {" +
                                MimicFightPatch.PatchUpdate.class.getName() + ".cal();" +
                                "}else{" +
                                "$_=$proceed($$);" +
                                "}" +
                                "}");
                    }
                }

                private boolean isMethodCalled(MethodCall m) {
                    if (!methodCalled && m.getMethodName().equals("hide")) {
                        methodCalled = true;
                        return true;
                    }
                    return false;
                }
            };
        }

        public static boolean check() {
            return AbstractDungeon.getCurrRoom().event instanceof MimicFight;
        }

        public static void cal() {
            AbstractDungeon.dungeonMapScreen.open(false);
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
        }
    }
}
