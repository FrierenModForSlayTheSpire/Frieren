package FrierenMod.patches;

import FrierenMod.cards.AbstractBaseCard;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.MedicalKit;

@SpirePatch(clz = MedicalKit.class, method = "onUseCard")
public class PatchMedicalKit {
    @SpireInsertPatch(rloc = 0,localvars = {"card","action"})
    public static SpireReturn<Void> prefix(MedicalKit _inst, AbstractCard card, AbstractGameAction action){
        if(card.hasTag(AbstractBaseCard.Enum.MANA))
            return SpireReturn.Return();
        return SpireReturn.Continue();
    }
}
