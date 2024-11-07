package FrierenMod.patches;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.cards.magicItems.props.UnbelievableTool;
import FrierenMod.enums.CharacterEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.gameHelpers.SlotBgHelper;
import FrierenMod.monsters.Spiegel_Frieren;
import FrierenMod.patches.fields.MagicDeckField;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.monsters.city.Chosen;
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;
import com.megacrit.cardcrawl.screens.DeathScreen;

import java.util.Arrays;

public class AchievementPatch {
    @SpirePatch(clz = CorruptHeart.class, method = "die")
    public static class PatchDie {
        @SpireInsertPatch(rloc = 5)
        public static void Insert() {
            if (AbstractDungeon.player.chosenClass == CharacterEnums.FRIEREN) {
                if (AbstractDungeon.ascensionLevel == 20)
                    SlotBgHelper.unlockANewSlot(SlotBgHelper.rollANewCommonSlotId('3'));
                if (AbstractDungeon.ascensionLevel >= 10)
                    SlotBgHelper.unlockANewSlot(SlotBgHelper.rollANewCommonSlotId('2'));
                SlotBgHelper.unlockANewSlot(SlotBgHelper.rollANewCommonSlotId('1'));
            }
        }
    }

    @SpirePatch(clz = AbstractMonster.class, method = "onFinalBossVictoryLogic")
    public static class PatchOnFinalBossVictoryLogic {
        @SpireInsertPatch(rloc = 12)
        public static void Insert(AbstractMonster __instance) {
            if (AbstractDungeon.player.chosenClass == CharacterEnums.FRIEREN) {
                if (AbstractDungeon.player.masterDeck.size() >= 40)
                    SlotBgHelper.unlockANewSlot("5008");
                if (MagicDeckField.getDeck().size() >= 30)
                    SlotBgHelper.unlockANewSlot("5009");
                if (Arrays.stream(CombatHelper.getLoadedMagicFactor()).filter(c -> c.magicItemRarity == AbstractMagicItem.MagicItemRarity.RARE).count() == 3)
                    SlotBgHelper.unlockANewSlot("5010");
                if (Arrays.stream(CombatHelper.getLoadedMagicFactor()).filter(c -> c.magicItemRarity == AbstractMagicItem.MagicItemRarity.COMMON).count() == 3)
                    SlotBgHelper.unlockANewSlot("5011");
                if (CardCrawlGame.playtime >= 7200.0F)
                    SlotBgHelper.unlockANewSlot("5012");
                if (Arrays.stream(CombatHelper.getLoadedMagicFactor()).map(factor -> factor.cardID).distinct().count() == 1)
                    SlotBgHelper.unlockANewSlot("5013");
                if (MagicDeckField.getDeck().group.stream().filter(factor -> factor.cardID.equals(UnbelievableTool.ID)).count() >= 5)
                    SlotBgHelper.unlockANewSlot("5014");
            }
        }
    }

    @SpirePatch(clz = DeathScreen.class, method = SpirePatch.CONSTRUCTOR)
    public static class PatchDeathScreen {
        @SpirePostfixPatch
        public static void Postfix(DeathScreen __instance, MonsterGroup m) {
            if (m != null) {
                if (AbstractDungeon.player.chosenClass == CharacterEnums.FRIEREN) {
                    SlotBgHelper.unlockANewSlot("0008");
                }
                for (AbstractMonster monster : m.monsters) {
                    if (monster.id.equals(Chosen.ID)) {
                        SlotBgHelper.unlockANewSlot("0009");
                    } else if (monster.id.equals(Spiegel_Frieren.MONSTER_ID)) {
                        SlotBgHelper.unlockANewSlot("0010");
                    }
                }
            }
        }
    }
}
