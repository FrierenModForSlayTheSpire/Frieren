package FrierenMod.patches;

import FrierenMod.cards.optionCards.magicItems.AbstractMagicItem;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.utils.PublicRes;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;

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
                if(((AbstractMagicItem) c).magicItemRarity == AbstractMagicItem.MagicItemRarity.PROP)
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
            if (c instanceof Mana) {
                return SpireReturn.Return(MANA_TEXTURE_IMG);
            } else
                return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "renderType")
    public static class PatchRenderType {
        @SpireInsertPatch(rloc = 25, localvars = {"sb", "typeColor"})
        public static SpireReturn<Void> Insert(AbstractCard _inst, SpriteBatch sb, Color typeColor) {
            if (_inst instanceof Mana) {
                FontHelper.renderRotatedText(sb, FontHelper.cardTypeFont, TEXT[0], _inst.current_x, _inst.current_y - 22.0F * _inst.drawScale * Settings.scale, 0.0F, -1.0F * _inst.drawScale * Settings.scale, _inst.angle, false, typeColor);
                return SpireReturn.Return();
            }
            if (_inst instanceof AbstractMagicItem) {
                if(((AbstractMagicItem) _inst).magicItemRarity == AbstractMagicItem.MagicItemRarity.PROP)
                    FontHelper.renderRotatedText(sb, FontHelper.cardTypeFont, TEXT[2], _inst.current_x, _inst.current_y - 22.0F * _inst.drawScale * Settings.scale, 0.0F, -1.0F * _inst.drawScale * Settings.scale, _inst.angle, false, typeColor);
                else
                    FontHelper.renderRotatedText(sb, FontHelper.cardTypeFont, TEXT[1], _inst.current_x, _inst.current_y - 22.0F * _inst.drawScale * Settings.scale, 0.0F, -1.0F * _inst.drawScale * Settings.scale, _inst.angle, false, typeColor);
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "renderBannerImage")
    public static class PatchRenderBannerImage {
        @SpireInsertPatch(rloc = 0, localvars = {"sb", "renderColor", "drawX", "drawY"})
        public static SpireReturn<Void> Insert(AbstractCard _inst, SpriteBatch sb, Color renderColor, float drawX, float drawY) {
            if (_inst instanceof AbstractMagicItem) {
                switch (((AbstractMagicItem) _inst).magicItemRarity) {
                    default:
                    case COMMON:
                    case BASIC:
                        renderHelper(_inst, sb, renderColor, ImageMaster.CARD_BANNER_COMMON, drawX, drawY);
                        return SpireReturn.Return();
                    case PROP:
                    case UNCOMMON:
                        renderHelper(_inst, sb, renderColor, ImageMaster.CARD_BANNER_UNCOMMON, drawX, drawY);
                        return SpireReturn.Return();
                    case RARE:
                        renderHelper(_inst, sb, renderColor, ImageMaster.CARD_BANNER_RARE, drawX, drawY);
                        return SpireReturn.Return();
                }
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "renderSkillBg")
    public static class PatchRenderSkillBg {
        private static final TextureAtlas.AtlasRegion MANA_TEXTURE_IMG = getImg(ImageMaster.loadImage(PublicRes.BG_MANA_512));

        @SpireInsertPatch(rloc = 0, localvars = {"sb", "renderColor", "x", "y"})
        public static SpireReturn<Void> Insert(AbstractCard _inst, SpriteBatch sb, Color renderColor, float x, float y) {
            if (_inst instanceof Mana) {
                renderHelper(_inst, sb, renderColor, MANA_TEXTURE_IMG, x, y);
                return SpireReturn.Return();
            } else {
                return SpireReturn.Continue();
            }
        }
    }

    private static void renderHelper(AbstractCard c, SpriteBatch sb, Color color, TextureAtlas.AtlasRegion img, float drawX, float drawY) {
        sb.setColor(color);
        sb.draw(img, drawX + img.offsetX - (float) img.originalWidth / 2.0F, drawY + img.offsetY - (float) img.originalHeight / 2.0F, (float) img.originalWidth / 2.0F - img.offsetX, (float) img.originalHeight / 2.0F - img.offsetY, (float) img.packedWidth, (float) img.packedHeight, c.drawScale * Settings.scale, c.drawScale * Settings.scale, c.angle);
    }

    private static TextureAtlas.AtlasRegion getImg(Texture texture) {
        return new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
    }
}
