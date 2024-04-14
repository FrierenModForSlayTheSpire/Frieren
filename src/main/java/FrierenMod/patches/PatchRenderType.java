package FrierenMod.patches;

import FrierenMod.cards.tempCards.Mana;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

import static com.megacrit.cardcrawl.helpers.FontHelper.cardTypeFont;

@SpirePatch(clz = AbstractCard.class,method = "renderType")
public class PatchRenderType {
    @SpireInsertPatch(rloc = 25,localvars = {"sb","typeColor"})
    public static SpireReturn Insert(AbstractCard _inst, SpriteBatch sb, Color typeColor){
        if(_inst instanceof Mana){
            FontHelper.renderRotatedText(sb, cardTypeFont, "Magic", _inst.current_x, _inst.current_y - 22.0F * _inst.drawScale * Settings.scale, 0.0F, -1.0F * _inst.drawScale * Settings.scale, _inst.angle, false, typeColor);
            return SpireReturn.Return();
        }else {
            return SpireReturn.Continue();
        }
    }
}
