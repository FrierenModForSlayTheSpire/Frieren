package FrierenMod.patches;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.patches.fields.CardRewardScreenField;
import FrierenMod.ui.slot.Slot;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;

public class CardRewardScreenPatch {
    @SpirePatch(clz = CardRewardScreen.class, method = "open")
    public static class PatchOpen {
        @SpirePrefixPatch
        public static void Prefix(CardRewardScreen __instance) {
            CardRewardScreenField.getSlots().clear();
        }
    }

    @SpirePatch(clz = CardRewardScreen.class, method = "customCombatOpen")
    public static class PatchCustomCombatOpen {
        @SpirePrefixPatch
        public static void Prefix(CardRewardScreen __instance) {
            CardRewardScreenField.getSlots().clear();
        }
    }

    @SpirePatch(clz = CardRewardScreen.class, method = "chooseOneOpen")
    public static class PatchChooseOneOpen {
        @SpirePrefixPatch
        public static void Prefix(CardRewardScreen __instance) {
            CardRewardScreenField.getSlots().clear();
        }
    }

    @SpirePatch(clz = CardRewardScreen.class, method = "draftOpen")
    public static class PatchDraftOpen {
        @SpirePrefixPatch
        public static void Prefix(CardRewardScreen __instance) {
            CardRewardScreenField.getSlots().clear();
        }
    }

    @SpirePatch(clz = CardRewardScreen.class, method = "renderCardReward")
    public static class PatchRenderCardReward {
        @SpireInsertPatch(rloc = 39)
        public static void Insert(CardRewardScreen __instance, SpriteBatch sb) {
            if (CardRewardScreenField.getSlots() == null || CardRewardScreenField.getSlots().size() != 3) {
                return;
            }
            for (AbstractCard c : AbstractDungeon.cardRewardScreen.rewardGroup) {
                if (!(c instanceof AbstractMagicItem))
                    return;
            }
            for (int i = 0; i < 3; i++) {
                Slot slot = CardRewardScreenField.getSlots().get(i);
                slot.setPosition(AbstractDungeon.cardRewardScreen.rewardGroup.get(i).current_x, AbstractDungeon.cardRewardScreen.rewardGroup.get(i).current_y);
                slot.update();
                slot.render(sb);
            }
        }
    }
}
