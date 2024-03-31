package FrierenMod.patches;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.enums.CharacterEnums;
import FrierenMod.gameHelpers.TopTextHelper;
import FrierenMod.utils.FrierenRes;
import basemod.ReflectionHacks;
import basemod.patches.com.megacrit.cardcrawl.screens.mainMenu.ColorTabBar.ColorTabBarFix;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatches2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen;
import com.megacrit.cardcrawl.screens.mainMenu.ColorTabBar;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;

import java.util.ArrayList;

@SpirePatches2({@SpirePatch2(clz = AbstractCard.class, method = "render", paramtypez = {SpriteBatch.class}), @SpirePatch2(clz = AbstractCard.class, method = "renderInLibrary", paramtypez = {SpriteBatch.class})})
public class RenderTopTextPatches {
    public static ArrayList<Class<? extends AbstractCard>> allowedCardClasses = new ArrayList<>();

    @SpirePostfixPatch
    public static void patch(AbstractCard __instance, SpriteBatch sb) {
        if (shouldShowTopText(__instance)) {
            String text = TopTextHelper.getTopTextById(__instance.cardID);
            if (text == null || __instance.isFlipped || __instance.isLocked || __instance.transparency <= 0.0F)
                return;
            BitmapFont font = FontHelper.cardTitleFont;
            float xPos = __instance.current_x;
            float yPos = __instance.current_y;
            float offsetY = 400.0F * Settings.scale * __instance.drawScale / 2.0F;
            BitmapFont.BitmapFontData fontData = font.getData();
            float originalScale = fontData.scaleX;
            float scaleMulti = 0.8F;
            int length = text.length();
            if (length > 20) {
                scaleMulti -= 0.02F * (length - 20);
                if (scaleMulti < 0.5F)
                    scaleMulti = 0.5F;
            }
            fontData.setScale(scaleMulti * __instance.drawScale * 0.85F);
            Color color = FrierenRes.RENDER_COLOR.cpy();
            color.a = __instance.transparency;
            FontHelper.renderRotatedText(sb, font, text, xPos, yPos, 0.0F, offsetY, __instance.angle, true, color);
            fontData.setScale(originalScale);
        }
    }

    public static boolean shouldShowTopText(AbstractCard c) {
        if (!Settings.hideCards && (isInFrierenRun() || isInFrierenCardLibraryScreen())) {
            if (c instanceof AbstractBaseCard)
                return true;
            for (Class<?> clazz : allowedCardClasses) {
                if (clazz.isAssignableFrom(c.getClass()))
                    return true;
            }
        }
        return false;
    }

    private static boolean isInFrierenRun() {
        return (AbstractDungeon.player != null && AbstractDungeon.player.chosenClass == CharacterEnums.FRIEREN);
    }

    public static boolean isInFrierenCardLibraryScreen() {
        if (!CardCrawlGame.isInARun() && CardCrawlGame.mainMenuScreen.screen == MainMenuScreen.CurScreen.CARD_LIBRARY) {
            ColorTabBar colorBar = (ColorTabBar)ReflectionHacks.getPrivate(CardCrawlGame.mainMenuScreen.cardLibraryScreen, CardLibraryScreen.class, "colorBar");
            if (colorBar.curTab == ColorTabBarFix.Enums.MOD) {
                ColorTabBarFix.ModColorTab modColorTab = ColorTabBarFix.Fields.getModTab();
                return (modColorTab != null && modColorTab.color == CardEnums.FRIEREN_CARD);
            }
        }
        return false;
    }
}