package FrierenMod.patches;

import FrierenMod.cards.optionCards.magicItems.AbstractMagicItem;
import FrierenMod.effects.FastMagicItemObtainEffect;
import FrierenMod.patches.fields.MagicItemBagField;
import basemod.ReflectionHacks;
import com.badlogic.gdx.math.Vector2;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.Soul;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.screens.CardRewardScreen;


public class MagicItemRewardPatch {
    public static class TypePatch {
        @SpireEnum
        public static RewardItem.RewardType MAGIC_ITEM_REWARD;
    }
    @SpirePatch(clz = CardRewardScreen.class, method = "acquireCard")
    public static class PatchAcquireCard {
        @SpireInsertPatch(rloc = 0, localvars = "hoveredCard")
        public static SpireReturn<Void> Insert(CardRewardScreen __instance, AbstractCard hoveredCard) {
            if (hoveredCard instanceof AbstractMagicItem) {
                InputHelper.justClickedLeft = false;
                AbstractDungeon.effectsQueue.add(new FastMagicItemObtainEffect(hoveredCard, hoveredCard.current_x, hoveredCard.current_y));
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = Soul.class, method = "obtain")
    public static class PatchObtain {
        private static final float MAGIC_BAG_X = (float) Settings.WIDTH - 220.0F * Settings.scale;
        private static final float MAGIC_BAG_Y = (float) Settings.HEIGHT - 32.0F * Settings.scale;

        @SpireInsertPatch(rloc = 0, localvars = "card")
        public static SpireReturn<Void> Insert(Soul __instance, AbstractCard card) {
            if (card instanceof AbstractMagicItem) {
                __instance.card = card;
                __instance.group = MagicItemBagField.getBag();
                __instance.group.addToTop(card);
                ReflectionHacks.setPrivate(__instance, Soul.class, "pos", new Vector2(card.current_x, card.current_y));
                ReflectionHacks.setPrivate(__instance, Soul.class, "target", new Vector2(MAGIC_BAG_X, MAGIC_BAG_Y));
                ReflectionHacks.privateMethod(Soul.class, "setSharedVariables").invoke(__instance);
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
}
