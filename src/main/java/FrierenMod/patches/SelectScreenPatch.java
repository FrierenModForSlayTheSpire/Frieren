package FrierenMod.patches;


import FrierenMod.enums.CharacterEnums;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.select.HandCardSelectScreen;

import java.util.Iterator;

public class SelectScreenPatch {
    @SpirePatch(clz = HandCardSelectScreen.class, method = "selectHoveredCard")
    public static class SelectScreenPatch1 {
        public static CardGroup handClone;

        public static CardGroup handCloneb;

        @SpirePostfixPatch
        public static void SelectionPostPatch(HandCardSelectScreen reg) {
            if (AbstractDungeon.player.chosenClass != CharacterEnums.FRIEREN || InputHelper.justClickedLeft || CInputActionSet.select.isJustPressed())
                ;
        }

        public static void ResetHand() {
            Iterator<AbstractCard> var1 = handClone.group.iterator();
            handCloneb = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            while (var1.hasNext()) {
                AbstractCard c = var1.next();
                for (AbstractCard d : AbstractDungeon.player.hand.group) {
                    if (c.equals(d)) {
                        handCloneb.addToBottom(c);
                        break;
                    }
                }
                AbstractDungeon.player.hand.removeCard(c);
            }
            for (AbstractCard c : handCloneb.group) {
                AbstractDungeon.player.hand.addToTop(c);
            }
            AbstractDungeon.player.hand.refreshHandLayout();
        }
    }

    @SpirePatch(clz = HandCardSelectScreen.class, method = "prep")
    public static class SelectScreenPatch2 {
        @SpirePrefixPatch
        public static SpireReturn<Void> SelectionPrePatch(HandCardSelectScreen reg) {
            if (AbstractDungeon.player.chosenClass == CharacterEnums.FRIEREN) {
                SelectScreenPatch1.handClone = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                for (AbstractCard c : AbstractDungeon.player.hand.group)
                    SelectScreenPatch1.handClone.addToBottom(c);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = HandCardSelectScreen.class, method = "updateSelectedCards")
    public static class SelectScreenPatch3 {
        @SpireInsertPatch(rloc = 34, localvars = {})
        public static void Insert() {
            if (AbstractDungeon.player.chosenClass == CharacterEnums.FRIEREN)
                SelectScreenPatch1.ResetHand();
        }
    }

    @SpirePatch(clz = HandCardSelectScreen.class, method = "selectHoveredCard")
    public static class SelectScreenPatch5 {
        @SpirePostfixPatch
        public static void SelectionPostPatch(HandCardSelectScreen reg) {
            if (AbstractDungeon.player.chosenClass == CharacterEnums.FRIEREN)
                SelectScreenPatch1.ResetHand();
        }
    }
}
