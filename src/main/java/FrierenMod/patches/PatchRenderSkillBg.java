package FrierenMod.patches;

import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;

import static FrierenMod.utils.FrierenRes.CHARACTER_NAME;

@SpirePatch(clz = AbstractCard.class,method = "renderSkillBg")
public class PatchRenderSkillBg {
    public static TextureAtlas.AtlasRegion MAGIC_POWER_BG = getImg(ImageMaster.loadImage(ModInformation.makeCardBgPath(CHARACTER_NAME,"magic",512)));
    @SpireInsertPatch(rloc = 0,localvars = {"sb","renderColor","x","y"})
    public static SpireReturn renderMagicPower(AbstractCard _inst,SpriteBatch sb,Color renderColor,float x,float y){
        if(_inst instanceof MagicPower){
            renderHelper(_inst,sb,renderColor,MAGIC_POWER_BG,x,y);
            return SpireReturn.Return();
        }
        else {
            return SpireReturn.Continue();
        }
    }
    private static void renderHelper(AbstractCard c, SpriteBatch sb, Color color, TextureAtlas.AtlasRegion img, float drawX, float drawY) {
        sb.setColor(color);
        sb.draw(img, drawX + img.offsetX - (float)img.originalWidth / 2.0F, drawY + img.offsetY - (float)img.originalHeight / 2.0F, (float)img.originalWidth / 2.0F - img.offsetX, (float)img.originalHeight / 2.0F - img.offsetY, (float)img.packedWidth, (float)img.packedHeight, c.drawScale * Settings.scale, c.drawScale * Settings.scale, c.angle);
    }
    private static TextureAtlas.AtlasRegion getImg(Texture texture){
        return new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
    }
}
