package FrierenMod.patches;

import FrierenMod.cards.tempCards.Mana;
import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.ExhaustBlurEffect;
import com.megacrit.cardcrawl.vfx.ExhaustEmberEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;


public class ManaExhaustAnimationPatch {
    @SpirePatch(clz = ExhaustCardEffect.class, method = SpirePatch.CONSTRUCTOR)
    public static class ExhaustCardEffectCtorPatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> prefix(ExhaustCardEffect __instance, AbstractCard card) {
            if (card instanceof Mana) {
                __instance.duration = 0.5F;
                ReflectionHacks.setPrivate(__instance, ExhaustCardEffect.class, "c", card);
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = ExhaustCardEffect.class, method = "update")
    public static class ExhaustCardEffectUpdatePatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> prefix(ExhaustCardEffect __instance) {
            AbstractCard c = ReflectionHacks.getPrivate(__instance, ExhaustCardEffect.class, "c");
            if (!(c instanceof Mana))
                return SpireReturn.Continue();
            else {
                if (__instance.duration == 0.5F) {
                    CardCrawlGame.sound.play("CARD_EXHAUST", 0.2F);
                    int i;
                    for (i = 0; i < 45; ++i) {
                        AbstractDungeon.effectsQueue.add(new ExhaustBlurEffect(c.current_x, c.current_y));
                    }

                    for (i = 0; i < 25; ++i) {
                        AbstractDungeon.effectsQueue.add(new ExhaustEmberEffect(c.current_x, c.current_y));
                    }
                }
                __instance.duration -= Gdx.graphics.getDeltaTime();
                if (!c.fadingOut && __instance.duration < 0.35F && !AbstractDungeon.player.hand.contains(c)) {
                    c.fadingOut = true;
                }
                if (__instance.duration < 0.0F) {
                    __instance.isDone = true;
                    c.resetAttributes();
                }
                return SpireReturn.Return();
            }
        }
    }

}
