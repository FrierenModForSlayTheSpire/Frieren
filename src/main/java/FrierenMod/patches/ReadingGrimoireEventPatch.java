package FrierenMod.patches;

import FrierenMod.effects.ReadingGrimoireEffect;
import FrierenMod.enums.CharacterEnums;
import FrierenMod.events.ReadingGrimoireEvent;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.Log;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.neow.NeowEvent;
import com.megacrit.cardcrawl.neow.NeowRoom;

public class ReadingGrimoireEventPatch {
    public static class ForceBlessing {
        @SpireInsertPatch(rloc = 1)
        public static void Insert(Object o, boolean b) {
            Settings.isTestingNeow = true;
        }
    }

    @SpirePatch(clz = NeowRoom.class, method = "<ctor>", paramtypez = {boolean.class})
    public static class AddBetterRewardsButton {
        public static String[] TEXT = CardCrawlGame.languagePack.getEventString(ReadingGrimoireEvent.ID).DESCRIPTIONS;

        @SpirePostfixPatch
        public static void Postfix(NeowRoom _inst, boolean b) {
            if (!b && AbstractDungeon.player.chosenClass == CharacterEnums.FRIEREN && !CombatHelper.isAllMagicFactorLoading()) {
                _inst.event.roomEventText.clear();
                _inst.event.roomEventText.addDialogOption(TEXT[0]);
            }
        }
    }

    @SpirePatch(clz = NeowEvent.class, method = "<ctor>", paramtypez = {boolean.class})
    public static class FixEventImage {
        @SpirePostfixPatch
        public static void Postfix(NeowEvent e, boolean b) {
            e.imageEventText.clear();
        }
    }

    @SpirePatch(clz = NeowEvent.class, method = "buttonEffect")
    public static class MaybeStartRewards {
        @SpireInsertPatch(rloc = 0)
        public static SpireReturn<Void> Prefix(NeowEvent _inst, int buttonPressed) {
            if (AbstractDungeon.player.chosenClass == CharacterEnums.FRIEREN && !CombatHelper.isAllMagicFactorLoading()) {
                ReflectionHacks.privateMethod(NeowEvent.class, "dismissBubble").invoke(_inst);
                MapRoomNode currNode = AbstractDungeon.getCurrMapNode();
                Log.logger.info("MapRoomNode currNode+{}  {}", currNode.x, currNode.y);
                AbstractDungeon.effectList.add(new ReadingGrimoireEffect());
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
}

