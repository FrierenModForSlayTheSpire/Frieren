package FrierenMod.patches;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.enums.CardEnums;
import FrierenMod.enums.CharacterEnums;
import FrierenMod.utils.FrierenRes;
import FrierenMod.utils.PublicRes;
import FrierenMod.utils.TopTextHelper;
import basemod.ReflectionHacks;
import basemod.patches.com.megacrit.cardcrawl.screens.mainMenu.ColorTabBar.ColorTabBarFix;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen;
import com.megacrit.cardcrawl.screens.mainMenu.ColorTabBar;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.vfx.cardManip.CardGlowBorder;
import javassist.CtBehavior;

import java.util.ArrayList;

public class CardStylePatch {
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString("FrierenMod:CardStyleText").TEXT;

    @SpirePatch(clz = SingleCardViewPopup.class, method = "renderCardTypeText")
    public static class PatchRenderCardTypeText {
        @SpireInsertPatch(rloc = 0, localvars = {"sb"})
        public static SpireReturn<Void> Insert(SingleCardViewPopup _inst, SpriteBatch sb) {
            AbstractCard c = ReflectionHacks.getPrivate(_inst, SingleCardViewPopup.class, "card");
            if (c instanceof Mana) {
                FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, TEXT[0], (float) Settings.WIDTH / 2.0F + 3.0F * Settings.scale, (float) Settings.HEIGHT / 2.0F - 40.0F * Settings.scale, ReflectionHacks.getPrivate(_inst, SingleCardViewPopup.class, "CARD_TYPE_COLOR"));
                return SpireReturn.Return();
            }
            if (c instanceof AbstractMagicItem) {
                if (((AbstractMagicItem) c).magicItemRarity == AbstractMagicItem.MagicItemRarity.PROP)
                    FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, TEXT[2], (float) Settings.WIDTH / 2.0F + 3.0F * Settings.scale, (float) Settings.HEIGHT / 2.0F - 40.0F * Settings.scale, ReflectionHacks.getPrivate(_inst, SingleCardViewPopup.class, "CARD_TYPE_COLOR"));
                else
                    FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, TEXT[1], (float) Settings.WIDTH / 2.0F + 3.0F * Settings.scale, (float) Settings.HEIGHT / 2.0F - 40.0F * Settings.scale, ReflectionHacks.getPrivate(_inst, SingleCardViewPopup.class, "CARD_TYPE_COLOR"));
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = SingleCardViewPopup.class, method = "getCardBackAtlasRegion")
    public static class PatchGetCardBackAtlasRegion {
        private static final TextureAtlas.AtlasRegion MANA_TEXTURE_IMG = getImg(ImageMaster.loadImage(PublicRes.BG_MANA_1024));

        @SpirePrefixPatch
        public static SpireReturn<TextureAtlas.AtlasRegion> Insert(SingleCardViewPopup _inst) {
            AbstractCard c = ReflectionHacks.getPrivate(_inst, SingleCardViewPopup.class, "card");
            if (c instanceof Mana || (c instanceof AbstractMagicItem && ((AbstractMagicItem) c).magicItemRarity != AbstractMagicItem.MagicItemRarity.PROP))
                return SpireReturn.Return(MANA_TEXTURE_IMG);
            if (c instanceof AbstractMagicItem && ((AbstractMagicItem) c).magicItemRarity == AbstractMagicItem.MagicItemRarity.PROP)
                return SpireReturn.Return(ImageMaster.CARD_SKILL_BG_GRAY_L);
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = SingleCardViewPopup.class, method = "renderCardBanner")
    public static class PatchRenderCardBanner {
        private static final TextureAtlas.AtlasRegion PROP_BANNER_L = getImg(ImageMaster.loadImage(PublicRes.CARD_BANNER_PROP_L));

        @SpirePrefixPatch
        public static SpireReturn<Void> Insert(SingleCardViewPopup _inst, SpriteBatch sb) {
            AbstractCard c = ReflectionHacks.getPrivate(_inst, SingleCardViewPopup.class, "card");
            if (c instanceof AbstractMagicItem) {
                TextureAtlas.AtlasRegion tmpImg = null;
                switch (((AbstractMagicItem) c).magicItemRarity) {
                    case BASIC:
                    case COMMON:
                        tmpImg = ImageMaster.CARD_BANNER_COMMON_L;
                        break;
                    case UNCOMMON:
                        tmpImg = ImageMaster.CARD_BANNER_UNCOMMON_L;
                        break;
                    case PROP:
                        tmpImg = PROP_BANNER_L;
                        break;
                    case RARE:
                        tmpImg = ImageMaster.CARD_BANNER_RARE_L;
                        break;
                }
                renderHelper(sb, (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F, tmpImg);
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }

        private static void renderHelper(SpriteBatch sb, float x, float y, TextureAtlas.AtlasRegion img) {
            if (img != null) {
                sb.draw(img, x + img.offsetX - (float) img.originalWidth / 2.0F, y + img.offsetY - (float) img.originalHeight / 2.0F, (float) img.originalWidth / 2.0F - img.offsetX, (float) img.originalHeight / 2.0F - img.offsetY, (float) img.packedWidth, (float) img.packedHeight, Settings.scale, Settings.scale, 0.0F);
            }
        }
    }

    @SpirePatch(clz = CardGlowBorder.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class, Color.class})
    public static class PatchCardGlowBorder {
        @SpireInsertPatch(rloc = 20, localvars = {"card", "gColor"})
        public static SpireReturn<Void> Insert(CardGlowBorder _inst, AbstractCard card, Color gColor) {
            if (card instanceof AbstractMagicItem) {
                _inst.duration = 1.2F;
                ReflectionHacks.setPrivate(_inst, CardGlowBorder.class, "card", card);
                ReflectionHacks.setPrivateInherited(_inst, CardGlowBorder.class, "color", gColor.cpy());
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }


    @SpirePatches2({@SpirePatch2(clz = AbstractCard.class, method = "render", paramtypez = {SpriteBatch.class}), @SpirePatch2(clz = AbstractCard.class, method = "renderInLibrary", paramtypez = {SpriteBatch.class})})
    public static class RenderTopTextPatches {
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
                ColorTabBar colorBar = (ColorTabBar) ReflectionHacks.getPrivate(CardCrawlGame.mainMenuScreen.cardLibraryScreen, CardLibraryScreen.class, "colorBar");
                if (colorBar.curTab == ColorTabBarFix.Enums.MOD) {
                    ColorTabBarFix.ModColorTab modColorTab = ColorTabBarFix.Fields.getModTab();
                    return (modColorTab != null && (modColorTab.color == CardEnums.FRIEREN_CARD || modColorTab.color == CardEnums.MAGIC_ITEM));
                }
            }
            return false;
        }
    }

    @SpirePatch(cls = "basemod.patches.com.megacrit.cardcrawl.screens.mainMenu.ColorTabBar.ColorTabBarFix$Render", method = "Insert", optional = true)
    public static class TabNamePatch {
        @SpireInsertPatch(locator = TabNameLocator.class, localvars = {"tabName"})
        public static void InsertFix(ColorTabBar _instance, SpriteBatch sb, float y, ColorTabBar.CurrentTab curTab, @ByRef String[] tabName) {
            if (tabName[0].equalsIgnoreCase(CardEnums.MAGIC_ITEM.name()))
                tabName[0] = TEXT[3];
        }

        private static class TabNameLocator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(FontHelper.class, "renderFontCentered");
                return LineFinder.findInOrder(ctMethodToPatch, (Matcher) methodCallMatcher);
            }
        }
    }

    private static TextureAtlas.AtlasRegion getImg(Texture texture) {
        return new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
    }
}
