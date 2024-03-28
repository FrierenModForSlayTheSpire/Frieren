package FrierenMod.patches;

import FrierenMod.gameHelpers.ChantHelper;
import FrierenMod.utils.ModInformation;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static FrierenMod.gameHelpers.HardCodedPowerHelper.MAGIC_INSTEAD_OF_COST;

@SpirePatch(clz = AbstractCard.class,method = "hasEnoughEnergy")
public class PatchHasEnoughEnergy {
    public static final String ID = ModInformation.makeID(PatchHasEnoughEnergy.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;
    @SpireInsertPatch(rloc = 35)
    public static SpireReturn<Boolean> Insert(AbstractCard _inst){
        AbstractPlayer p = AbstractDungeon.player;
        if(p.hasPower(MAGIC_INSTEAD_OF_COST)){
            if(ChantHelper.getAllManaNum() >= _inst.costForTurn)
                return SpireReturn.Return(true);
            else {
                _inst.cantUseMessage = TEXT[0];
                return SpireReturn.Return(false);
            }
        }
        return SpireReturn.Continue();
    }
}
