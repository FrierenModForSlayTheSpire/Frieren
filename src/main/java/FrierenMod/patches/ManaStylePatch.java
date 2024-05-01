package FrierenMod.patches;

import FrierenMod.cards.tempCards.Mana;
import FrierenMod.utils.PublicRes;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;

public class ManaStylePatch {
    @SpirePatch(clz = SingleCardViewPopup.class, method = "renderCardTypeText")
    public static class PatchRenderCardTypeText {
        @SpireInsertPatch(rloc = 0, localvars = {"sb"})
        public static SpireReturn<Void> Insert(SingleCardViewPopup _inst, SpriteBatch sb) {
            AbstractCard c = ReflectionHacks.getPrivate(_inst, SingleCardViewPopup.class, "card");
            if (c instanceof Mana) {
                FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, "Mana", (float) Settings.WIDTH / 2.0F + 3.0F * Settings.scale, (float) Settings.HEIGHT / 2.0F - 40.0F * Settings.scale, ReflectionHacks.getPrivate(_inst, SingleCardViewPopup.class, "CARD_TYPE_COLOR"));
                return SpireReturn.Return();
            } else
                return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = SingleCardViewPopup.class, method = "getCardBackAtlasRegion")
    public static class PatchGetCardBackAtlasRegion {
        @SpirePrefixPatch
        public static SpireReturn<TextureAtlas.AtlasRegion> Insert(SingleCardViewPopup _inst) {
            AbstractCard c = ReflectionHacks.getPrivate(_inst, SingleCardViewPopup.class, "card");
            if (c instanceof Mana) {
                return SpireReturn.Return(getImg(PublicRes.BG_MANA_1024));
            } else
                return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "renderType")
    public static class PatchRenderType {
        @SpireInsertPatch(rloc = 25, localvars = {"sb", "typeColor"})
        public static SpireReturn<Void> Insert(AbstractCard _inst, SpriteBatch sb, Color typeColor) {
            if (_inst instanceof Mana) {
                FontHelper.renderRotatedText(sb, FontHelper.cardTypeFont, "Mana", _inst.current_x, _inst.current_y - 22.0F * _inst.drawScale * Settings.scale, 0.0F, -1.0F * _inst.drawScale * Settings.scale, _inst.angle, false, typeColor);
                return SpireReturn.Return();
            } else {
                return SpireReturn.Continue();
            }
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "renderSkillBg")
    public static class PatchRenderSkillBg {
        @SpireInsertPatch(rloc = 0, localvars = {"sb", "renderColor", "x", "y"})
        public static SpireReturn<Void> Insert(AbstractCard _inst, SpriteBatch sb, Color renderColor, float x, float y) {
            if (_inst instanceof Mana) {
                renderHelper(_inst, sb, renderColor, getImg(PublicRes.BG_MANA_512), x, y);
                return SpireReturn.Return();
            } else {
                return SpireReturn.Continue();
            }
        }

        private static void renderHelper(AbstractCard c, SpriteBatch sb, Color color, TextureAtlas.AtlasRegion img, float drawX, float drawY) {
            sb.setColor(color);
            sb.draw(img, drawX + img.offsetX - (float) img.originalWidth / 2.0F, drawY + img.offsetY - (float) img.originalHeight / 2.0F, (float) img.originalWidth / 2.0F - img.offsetX, (float) img.originalHeight / 2.0F - img.offsetY, (float) img.packedWidth, (float) img.packedHeight, c.drawScale * Settings.scale, c.drawScale * Settings.scale, c.angle);
        }
    }

    private static TextureAtlas.AtlasRegion getImg(String imgUrl) {
        return new TextureAtlas.AtlasRegion(ImageMaster.loadImage(imgUrl), 0, 0, ImageMaster.loadImage(imgUrl).getWidth(), ImageMaster.loadImage(imgUrl).getHeight());
    }
}
