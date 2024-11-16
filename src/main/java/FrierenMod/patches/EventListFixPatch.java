package FrierenMod.patches;

import FrierenMod.enums.CharacterEnums;
import FrierenMod.events.AnimalWell;
import FrierenMod.events.FoodEvent;
import FrierenMod.events.KraftGift;
import FrierenMod.events.MimicFight;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;

import java.util.ArrayList;

public class EventListFixPatch {
    public static ArrayList<String> getFrierenEventList() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(FoodEvent.ID);
        retVal.add(KraftGift.ID);
        retVal.add(AnimalWell.ID);
        retVal.add(MimicFight.ID);
        return retVal;
    }

    @SpirePatch(clz = Exordium.class, method = "initializeEventList")
    public static class PatchExordium {
        @SpirePostfixPatch
        public static void Postfix(Exordium __instance) {
            if (AbstractDungeon.player.chosenClass == CharacterEnums.FERN) {
                for (String id : getFrierenEventList()) {
                    AbstractDungeon.eventList.remove(id);
                }
            }
        }
    }
    @SpirePatch(clz = TheBeyond.class, method = "initializeEventList")
    public static class PatchTheBeyond {
        @SpirePostfixPatch
        public static void Postfix(TheBeyond __instance) {
            if (AbstractDungeon.player.chosenClass == CharacterEnums.FERN) {
                for (String id : getFrierenEventList()) {
                    AbstractDungeon.eventList.remove(id);
                }
            }
        }
    }
    @SpirePatch(clz = TheCity.class, method = "initializeEventList")
    public static class PatchTheCity {
        @SpirePostfixPatch
        public static void Postfix(TheCity __instance) {
            if (AbstractDungeon.player.chosenClass == CharacterEnums.FERN) {
                for (String id : getFrierenEventList()) {
                    AbstractDungeon.eventList.remove(id);
                }
            }
        }
    }
}
