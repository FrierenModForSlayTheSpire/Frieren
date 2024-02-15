package FrierenMod.patches;

import FrierenMod.gameHelpers.ChantHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(clz= AbstractCard.class,method = "canUse")
public class PatchCanUse{
    @SpirePrefixPatch
    public static SpireReturn<Boolean> ImaginationCanUse(AbstractCard _inst){
        if(AbstractDungeon.player.hasPower("FrierenMod:ImaginationPower") && ChantHelper.getAllMagicPowerNum() < _inst.cost){
            return SpireReturn.Return(false);
        }else {
            return SpireReturn.Continue();
        }
    }
}