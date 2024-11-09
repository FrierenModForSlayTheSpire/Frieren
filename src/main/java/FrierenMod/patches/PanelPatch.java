package FrierenMod.patches;

import FrierenMod.ui.panels.FernPanel;
import FrierenMod.ui.panels.ManaPanel;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.buttons.EndTurnButton;
import com.megacrit.cardcrawl.ui.panels.DrawPilePanel;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import javassist.CannotCompileException;
import javassist.CtBehavior;

public class PanelPatch {
    @SpirePatch2(clz = AbstractPlayer.class, method = "<class>")
    public static class PanelField {
        public static SpireField<FrierenMod.ui.panels.ManaPanel> ManaPanel = new SpireField<>(ManaPanel::new);
        public static SpireField<FrierenMod.ui.panels.FernPanel> FernPanel = new SpireField<>(FernPanel::new);
    }

    @SpirePatch2(clz = GameActionManager.class, method = "clear")
    public static class ClearMPAfterCombat {
        @SpireInsertPatch(locator = ClearMPAfterCombatLocator.class)
        public static void ClearMP() {
            if (AbstractDungeon.player != null) {
                (PanelField.ManaPanel.get(AbstractDungeon.player)).clearMP();
                (PanelField.FernPanel.get(AbstractDungeon.player)).clearMP();
            }
        }

        public static class ClearMPAfterCombatLocator extends SpireInsertLocator {
            public int[] Locate(CtBehavior behavior) throws PatchingException, CannotCompileException {
                Matcher.FieldAccessMatcher matcher = new Matcher.FieldAccessMatcher(GameActionManager.class, "mantraGained");
                return LineFinder.findInOrder(behavior, (Matcher) matcher);
            }
        }
    }

    @SpirePatch2(clz = OverlayMenu.class, method = "showCombatPanels")
    public static class ShowPanel {
        @SpireInsertPatch(locator = ShowPanelLocator.class)
        public static void Show() {
            if (AbstractDungeon.player != null) {
                (PanelField.ManaPanel.get(AbstractDungeon.player)).show();
                (PanelField.FernPanel.get(AbstractDungeon.player)).show();
            }
        }

        public static class ShowPanelLocator extends SpireInsertLocator {
            public int[] Locate(CtBehavior behavior) throws PatchingException, CannotCompileException {
                Matcher.MethodCallMatcher matcher = new Matcher.MethodCallMatcher(EndTurnButton.class, "show");
                return LineFinder.findInOrder(behavior, (Matcher) matcher);
            }
        }
    }

    @SpirePatch2(clz = OverlayMenu.class, method = "hideCombatPanels")
    public static class HidePanel {
        @SpireInsertPatch(locator = HidePanelLocator.class)
        public static void Hide() {
            if (AbstractDungeon.player != null) {
                (PanelField.ManaPanel.get(AbstractDungeon.player)).hide();
                (PanelField.FernPanel.get(AbstractDungeon.player)).hide();
            }
        }

        public static class HidePanelLocator extends SpireInsertLocator {
            public int[] Locate(CtBehavior behavior) throws PatchingException, CannotCompileException {
                Matcher.MethodCallMatcher matcher = new Matcher.MethodCallMatcher(EnergyPanel.class, "hide");
                return LineFinder.findInOrder(behavior, (Matcher) matcher);
            }
        }
    }

    @SpirePatch2(clz = OverlayMenu.class, method = "render")
    public static class RenderPanel {
        @SpireInsertPatch(locator = RenderPanelLocator.class)
        public static void Render(SpriteBatch sb) {
            if (AbstractDungeon.player != null) {
                (PanelField.ManaPanel.get(AbstractDungeon.player)).render(sb);
                (PanelField.FernPanel.get(AbstractDungeon.player)).render(sb);
            }
        }

        public static class RenderPanelLocator extends SpireInsertLocator {
            public int[] Locate(CtBehavior behavior) throws PatchingException, CannotCompileException {
                Matcher.MethodCallMatcher matcher = new Matcher.MethodCallMatcher(DrawPilePanel.class, "render");
                return LineFinder.findInOrder(behavior, (Matcher) matcher);
            }
        }
    }

    @SpirePatch2(clz = OverlayMenu.class, method = "update")
    public static class UpdatePanel {
        @SpireInsertPatch(locator = UpdatePanelLocator.class)
        public static void Update() {
            if (AbstractDungeon.player != null) {
                (PanelField.ManaPanel.get(AbstractDungeon.player)).updatePositions();
                (PanelField.ManaPanel.get(AbstractDungeon.player)).update();
                (PanelField.FernPanel.get(AbstractDungeon.player)).updatePositions();
                (PanelField.FernPanel.get(AbstractDungeon.player)).update();
            }
        }

        public static class UpdatePanelLocator extends SpireInsertLocator {
            public int[] Locate(CtBehavior behavior) throws PatchingException, CannotCompileException {
                Matcher.MethodCallMatcher matcher = new Matcher.MethodCallMatcher(CardGroup.class, "update");
                return LineFinder.findInOrder(behavior, (Matcher) matcher);
            }
        }
    }
}
