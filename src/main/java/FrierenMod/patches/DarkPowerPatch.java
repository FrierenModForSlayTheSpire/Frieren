package FrierenMod.patches;

import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.powers.DarkPower;
import FrierenMod.utils.ModInformation;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.scenes.*;
import com.megacrit.cardcrawl.ui.panels.*;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public class DarkPowerPatch {
    public static class MakeBgBlack {
        private static final int HIDE_LEVEL = 2;

        @SpirePatch(clz = TopPanel.class, method = "render")
        public static class PatchRender {

            private static final Texture bar = ImageMaster.loadImage(ModInformation.makeUIPath("bar"));

            @SpireInsertPatch(rloc = 5, localvars = {"sb"})
            public static void Insert(TopPanel __instance, SpriteBatch sb) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    float H = ReflectionHacks.getPrivate(__instance, TopPanel.class, "TOPBAR_H");
                    sb.draw(bar, 0.0F, Settings.HEIGHT - H, Settings.WIDTH, H);
                }
            }
        }
    }

    public static class HideTips {
        private static final int HIDE_LEVEL = 2;

        @SpirePatch(clz = TopPanel.class, method = "updateTips")
        public static class PatchUpdatePotions {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(TopPanel __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
    }

    public static class HidePotions {
        private static final int HIDE_LEVEL = 2;

        @SpirePatch(clz = TopPanel.class, method = "updatePotions")
        public static class PatchUpdatePotions {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(TopPanel __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }

        @SpirePatch(clz = AbstractPotion.class, method = "render")
        public static class PatchRender {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(AbstractPotion __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }

        @SpirePatch(clz = AbstractPotion.class, method = "renderOutline", paramtypez = {SpriteBatch.class})
        public static class PatchRenderOutline {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(AbstractPotion __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }

        @SpirePatch(clz = AbstractPotion.class, method = "renderLightOutline")
        public static class PatchRenderLightOutline {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(AbstractPotion __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }

        @SpirePatch(clz = AbstractPotion.class, method = "renderShiny")
        public static class PatchRenderShiny {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(AbstractPotion __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
    }

    public static class HideState {
        private static final int HIDE_LEVEL = 2;

        @SpirePatch(clz = TopPanel.class, method = "renderHP")
        public static class PatchRenderHP {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(TopPanel __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
        @SpirePatch(clz = AbstractCreature.class, method = "renderHealth")
        public static class PatchRenderHealth {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(AbstractCreature __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
        @SpirePatch(clz = AbstractMonster.class, method = "renderIntentVfxBehind")
        public static class PatchRenderIntentVfxBehind {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(AbstractMonster __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
        @SpirePatch(clz = AbstractMonster.class, method = "renderIntent")
        public static class PatchRenderIntent {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(AbstractMonster __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
        @SpirePatch(clz = AbstractMonster.class, method = "renderIntentVfxAfter")
        public static class PatchRenderIntentVfxAfter {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(AbstractMonster __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
        @SpirePatch(clz = AbstractMonster.class, method = "renderDamageRange")
        public static class PatchRenderDamageRange {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(AbstractMonster __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
        @SpirePatch(clz = AbstractMonster.class, method = "renderName")
        public static class PatchRenderName {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(AbstractMonster __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
        @SpirePatch(clz = AbstractMonster.class, method = "renderTip")
        public static class PatchRenderTip {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(AbstractMonster __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
        @SpirePatch(clz = AbstractPlayer.class, method = "renderPlayerBattleUi")
        public static class PatchRenderPlayerBattleUi {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(AbstractPlayer __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
    }

    public static class HideGold {
        private static final int HIDE_LEVEL = 2;

        @SpirePatch(clz = TopPanel.class, method = "renderGold")
        public static class PatchRenderGold {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(TopPanel __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
    }

    public static class HideMap {
        private static final int HIDE_LEVEL = 2;

        @SpirePatch(clz = TopPanel.class, method = "renderMapIcon")
        public static class PatchRenderMapIcon {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(TopPanel __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }

        @SpirePatch(clz = TopPanel.class, method = "updateMapButtonLogic")
        public static class PatchUpdateMapButtonLogic {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(TopPanel __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
    }

    public static class HideDeck {
        private static final int HIDE_LEVEL = 2;

        @SpirePatch(clz = TopPanel.class, method = "renderDeckIcon")
        public static class PatchRenderDeckIcon {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(TopPanel __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }

        @SpirePatch(clz = TopPanel.class, method = "updateDeckViewButtonLogic")
        public static class PatchUpdateDeckViewButtonLogic {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(TopPanel __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }

        @SpirePatch(clz = TopPanel.class, method = "renderTopRightIcons")
        public static class PatchRenderTopRightIcons {
            @SpireInstrumentPatch
            public static ExprEditor Instrument() {
                return new ExprEditor() {
                    @Override
                    public void edit(MethodCall m) throws CannotCompileException {
                        if (isMethodCalled(m)) {
                            m.replace("{" +
                                    "if (" + PatchRenderTopRightIcons.class.getName() + ".check()) {" +
                                    "}else{" +
                                    "$_=$proceed($$);" +
                                    "}" +
                                    "}");
                        }
                    }

                    private boolean isMethodCalled(MethodCall m) {
                        return m.getMethodName().equals("renderFontRightTopAligned");
                    }
                };
            }
            public static boolean check(){
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                return po instanceof DarkPower && po.amount < HIDE_LEVEL;
            }
        }
    }

    public static class HideName {
        private static final int HIDE_LEVEL = 3;

        @SpirePatch(clz = TopPanel.class, method = "renderName")
        public static class PatchRenderName {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(TopPanel __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
    }

    public static class HideDungeonInfo {
        private static final int HIDE_LEVEL = 3;

        @SpirePatch(clz = TopPanel.class, method = "renderDungeonInfo")
        public static class PatchRenderDungeonInfo {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(TopPanel __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }

        @SpirePatch(clz = TopPanel.class, method = "updateAscensionHover")
        public static class PatchUpdateAscensionHover {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(TopPanel __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
    }

    public static class HideRelics {
        private static final int HIDE_LEVEL = 2;

        @SpirePatch(clz = TopPanel.class, method = "renderRelics")
        public static class PatchRenderRelics {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(TopPanel __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
    }

    public static class HideModTips {
        private static final int HIDE_LEVEL = 2;

        @SpirePatch(clz = TopPanel.class, method = "renderModTips")
        public static class PatchRenderModTips {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(TopPanel __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
    }
    public static class HideBg {
        private static final int HIDE_LEVEL = 999;

        @SpirePatch(clz = TheBeyondScene.class, method = "renderCombatRoomBg")
        public static class PatchRenderCombatRoomBg {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(AbstractScene __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
        @SpirePatch(clz = TheBeyondScene.class, method = "renderCombatRoomFg")
        public static class PatchRenderCombatRoomFg {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(AbstractScene __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
        @SpirePatch(clz = TheBottomScene.class, method = "renderCombatRoomBg")
        public static class PatchRenderCombatRoomBg2 {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(AbstractScene __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
        @SpirePatch(clz = TheBottomScene.class, method = "renderCombatRoomFg")
        public static class PatchRenderCombatRoomFg2 {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(AbstractScene __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
        @SpirePatch(clz = TheCityScene.class, method = "renderCombatRoomBg")
        public static class PatchRenderCombatRoomBg3 {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(AbstractScene __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
        @SpirePatch(clz = TheCityScene.class, method = "renderCombatRoomFg")
        public static class PatchRenderCombatRoomFg3 {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(AbstractScene __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
        @SpirePatch(clz = TheEndingScene.class, method = "renderCombatRoomBg")
        public static class PatchRenderCombatRoomBg4 {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(AbstractScene __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
        @SpirePatch(clz = TheEndingScene.class, method = "renderCombatRoomFg")
        public static class PatchRenderCombatRoomFg4 {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(AbstractScene __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
    }
    public static class HideEnergyPanel{
        private static final int HIDE_LEVEL = 4;

        @SpirePatch(clz = EnergyPanel.class, method = "render")
        public static class PatchRender {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(EnergyPanel __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
    }
    public static class HideDrawPilePanel{
        private static final int HIDE_LEVEL = 3;

        @SpirePatch(clz = DrawPilePanel.class, method = "render")
        public static class PatchRender {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(DrawPilePanel __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
    }
    public static class HideDiscardPilePanel{
        private static final int HIDE_LEVEL = 4;

        @SpirePatch(clz = DiscardPilePanel.class, method = "render")
        public static class PatchRender {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(DiscardPilePanel __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
    }
    public static class HideExhaustPanel{
        private static final int HIDE_LEVEL = 4;

        @SpirePatch(clz = ExhaustPanel.class, method = "render")
        public static class PatchRender {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(ExhaustPanel __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
        @SpirePatch(clz = ExhaustPanel.class, method = "updateVfx")
        public static class PatchUpdateVfx {
            @SpirePrefixPatch
            public static SpireReturn<Void> Prefix(ExhaustPanel __instance) {
                AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                if (po instanceof DarkPower && po.amount < HIDE_LEVEL) {
                    return SpireReturn.Return();
                }
                return SpireReturn.Continue();
            }
        }
    }
    public static class HideVersionInfo{
        @SpirePatch(clz = CardCrawlGame.class, method = "update")
        public static class PatchUpdate {
            private static final int HIDE_LEVEL = 4;
            @SpireInsertPatch(rloc = 20)
            public static void Insert(CardCrawlGame ccg) {
                CardCrawlGame.displayVersion = true;
                if(CombatHelper.isInCombat()){
                    AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
                    if (po instanceof DarkPower && po.amount < HIDE_LEVEL)
                        CardCrawlGame.displayVersion = false;
                }
            }
        }
    }
}
