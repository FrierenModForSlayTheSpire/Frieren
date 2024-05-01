package FrierenMod.patches;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.powers.ManaInsteadOfEnergyPower;
import FrierenMod.utils.ModInformation;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(clz = AbstractCard.class,method = "hasEnoughEnergy")
public class PatchHasEnoughEnergy {
    public static final String ID = ModInformation.makeID(PatchHasEnoughEnergy.class.getSimpleName());
    @SpireInsertPatch(rloc = 35)
    public static SpireReturn<Boolean> Insert(AbstractCard _inst){
        AbstractPlayer p = AbstractDungeon.player;
        if(p.hasPower(ManaInsteadOfEnergyPower.POWER_ID)){
            if(CombatHelper.getAllManaNum() >= _inst.costForTurn)
                return SpireReturn.Return(true);
            else {
                _inst.cantUseMessage = AbstractBaseCard.cantUseTEXT[0];
                return SpireReturn.Return(false);
            }
        }
        return SpireReturn.Continue();
    }
}
