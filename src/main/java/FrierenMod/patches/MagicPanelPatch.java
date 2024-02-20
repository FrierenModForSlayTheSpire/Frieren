package FrierenMod.patches;

import FrierenMod.panels.MagicPanel;
import FrierenMod.utils.GlobalConfig;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.buttons.EndTurnButton;
import com.megacrit.cardcrawl.ui.panels.DrawPilePanel;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import javassist.CannotCompileException;
import javassist.CtBehavior;

public class MagicPanelPatch {
    @SpirePatch2(clz = GameActionManager.class, method = "clear")
    public static class ClearMPAfterCombat {
        @SpireInsertPatch(locator = ClearMPAfterCombatLocator.class)
        public static void ClearMP() {
            if (GlobalConfig.ActivatePatchMPField &&
                    AbstractDungeon.player != null) {
                ((MagicPanel)MPField.Panel.get(AbstractDungeon.player)).clearMP();
            }
        }

        public static class ClearMPAfterCombatLocator extends SpireInsertLocator {
            public int[] Locate(CtBehavior behavior) throws PatchingException, CannotCompileException {
                Matcher.FieldAccessMatcher matcher = new Matcher.FieldAccessMatcher(GameActionManager.class, "mantraGained");
                return LineFinder.findInOrder(behavior, (Matcher)matcher);
            }
        }
    }

    @SpirePatch2(clz = OverlayMenu.class, method = "showCombatPanels")
    public static class ShowPanel {
        @SpireInsertPatch(locator = ShowPanelLocator.class)
        public static void Show() {
            if (GlobalConfig.ActivatePatchMPField &&
                    AbstractDungeon.player != null)
                ((MagicPanel)MPField.Panel.get(AbstractDungeon.player)).show();
        }

        public static class ShowPanelLocator extends SpireInsertLocator {
            public int[] Locate(CtBehavior behavior) throws PatchingException, CannotCompileException {
                Matcher.MethodCallMatcher matcher = new Matcher.MethodCallMatcher(EndTurnButton.class, "show");
                return LineFinder.findInOrder(behavior, (Matcher)matcher);
            }
        }
    }

    @SpirePatch2(clz = OverlayMenu.class, method = "hideCombatPanels")
    public static class HidePanel {
        @SpireInsertPatch(locator = HidePanelLocator.class)
        public static void Hide() {
            if (GlobalConfig.ActivatePatchMPField &&
                    AbstractDungeon.player != null)
                ((MagicPanel)MPField.Panel.get(AbstractDungeon.player)).hide();
        }

        public static class HidePanelLocator extends SpireInsertLocator {
            public int[] Locate(CtBehavior behavior) throws PatchingException, CannotCompileException {
                Matcher.MethodCallMatcher matcher = new Matcher.MethodCallMatcher(EnergyPanel.class, "hide");
                return LineFinder.findInOrder(behavior, (Matcher)matcher);
            }
        }
    }

    @SpirePatch2(clz = OverlayMenu.class, method = "render")
    public static class RenderPanel {
        @SpireInsertPatch(locator = RenderPanelLocator.class)
        public static void Render(SpriteBatch sb) {
            if (GlobalConfig.ActivatePatchMPField &&
                    AbstractDungeon.player != null)
                ((MagicPanel)MPField.Panel.get(AbstractDungeon.player)).render(sb);
        }

        public static class RenderPanelLocator extends SpireInsertLocator {
            public int[] Locate(CtBehavior behavior) throws PatchingException, CannotCompileException {
                Matcher.MethodCallMatcher matcher = new Matcher.MethodCallMatcher(DrawPilePanel.class, "render");
                return LineFinder.findInOrder(behavior, (Matcher)matcher);
            }
        }
    }

    @SpirePatch2(clz = OverlayMenu.class, method = "update")
    public static class UpdatePanel {
        @SpireInsertPatch(locator = UpdatePanelLocator.class)
        public static void Update() {
            if (GlobalConfig.ActivatePatchMPField &&
                    AbstractDungeon.player != null) {
                ((MagicPanel)MPField.Panel.get(AbstractDungeon.player)).updatePositions();
                ((MagicPanel)MPField.Panel.get(AbstractDungeon.player)).update();
            }
        }

        public static class UpdatePanelLocator extends SpireInsertLocator {
            public int[] Locate(CtBehavior behavior) throws PatchingException, CannotCompileException {
                Matcher.MethodCallMatcher matcher = new Matcher.MethodCallMatcher(CardGroup.class, "update");
                return LineFinder.findInOrder(behavior, (Matcher)matcher);
            }
        }
    }
}
