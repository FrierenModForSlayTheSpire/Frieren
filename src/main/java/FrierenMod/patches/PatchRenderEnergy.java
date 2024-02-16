package FrierenMod.patches;

import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;

import static FrierenMod.gameHelpers.HardCodedPowerHelper.MAGIC_INSTEAD_OF_COST;
import static FrierenMod.utils.FrierenRes.CHARACTER_NAME;

@SpirePatch(clz = AbstractCard.class, method = "renderEnergy")
public class PatchRenderEnergy {
    public static TextureAtlas.AtlasRegion MAGIC_ORB = getImg(ImageMaster.loadImage(ModInformation.makeCardOrbPath(CHARACTER_NAME,"magic")));
    @SpireInsertPatch(rloc = 25, localvars = {"sb","renderColor"})
    public static void Insert(AbstractCard _inst, SpriteBatch sb, Color renderColor){
        AbstractPlayer p = AbstractDungeon.player;
        if(p != null && p.hasPower(MAGIC_INSTEAD_OF_COST) && _inst.cost >= 0)
            renderHelper2(_inst, sb, renderColor, MAGIC_ORB, _inst.current_x, _inst.current_y);
    }
    private static void renderHelper2(AbstractCard c, SpriteBatch sb, Color color, TextureAtlas.AtlasRegion img, float drawX, float drawY) {
        sb.setColor(color);
        sb.draw(img, drawX + img.offsetX - img.originalWidth / 2.0F, drawY + img.offsetY - img.originalHeight / 2.0F, img.originalWidth / 2.0F - img.offsetX, img.originalHeight / 2.0F - img.offsetY, img.packedWidth, img.packedHeight, c.drawScale * Settings.scale, c.drawScale * Settings.scale, c.angle);
    }
    private static TextureAtlas.AtlasRegion getImg(Texture texture){
        return new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
    }
}
