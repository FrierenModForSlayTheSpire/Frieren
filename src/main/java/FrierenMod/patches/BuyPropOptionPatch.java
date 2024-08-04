package FrierenMod.patches;

import FrierenMod.enums.CharacterEnums;
import FrierenMod.ui.campfire.BuyPropOption;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

import java.util.ArrayList;

@SpirePatch(clz = CampfireUI.class,method = "initializeButtons")
public class BuyPropOptionPatch {
    @SpireInsertPatch(rloc = 5)
    public static void Insert(CampfireUI __instance) {
        if(AbstractDungeon.player.chosenClass == CharacterEnums.FRIEREN){
            ArrayList<AbstractCampfireOption> buttons = ReflectionHacks.getPrivate(__instance, CampfireUI.class,"buttons");
            buttons.add(new BuyPropOption(true));
        }
    }
}
