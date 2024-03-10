package FrierenMod.patches;

import FrierenMod.cards.AbstractFrierenCard;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

@SpirePatch(clz = AbstractPlayer.class, method = "applyStartOfCombatLogic")

public class PatchApplyStartOfCombatLogic {
    @SpirePrefixPatch
    public static void cardAtBattleStart(AbstractPlayer _inst){
        triggerAllCardsInGroup(_inst.drawPile);
        triggerAllCardsInGroup(_inst.hand);
        triggerAllCardsInGroup(_inst.discardPile);
    }
    private static void triggerAllCardsInGroup(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group) {
            if(c instanceof AbstractFrierenCard){
                ((AbstractFrierenCard) c).atBattleStart();
            }
        }
    }
}
