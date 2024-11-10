package FrierenMod.patches;


import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import static com.megacrit.cardcrawl.unlock.UnlockTracker.seenPref;

@SpirePatch(clz = UnlockTracker.class, method = "markCardAsSeen")
public class PatchMarkCardAsSeen {
    @SpirePostfixPatch
    public static void PostFix(String key) {
        String dualCardKey = key + "$";
        if (CardLibrary.getCard(dualCardKey) != null && !CardLibrary.getCard(dualCardKey).isSeen) {
            CardLibrary.getCard(key).isSeen = true;
            seenPref.putInteger(key, 1);
            seenPref.flush();
        }
    }
}
