package FrierenMod.cardMods;

import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class RecoverCardDescriptionAfterImaginationPowerMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(RecoverCardDescriptionAfterImaginationPowerMod.class.getSimpleName());

    public RecoverCardDescriptionAfterImaginationPowerMod() {
    }

    public AbstractCardModifier makeCopy() {
        return new RecoverCardDescriptionAfterImaginationPowerMod();
    }

    public void onInitialApplication(AbstractCard card) {
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return removeFirstSubstring(rawDescription," NL ");
    }
    public static String removeFirstSubstring(String mainString, String subStringToRemove) {
        int index = mainString.indexOf(subStringToRemove);
        if (index != -1) {
            return mainString.substring(index + subStringToRemove.length());
        } else {
            return mainString;
        }
    }
}
