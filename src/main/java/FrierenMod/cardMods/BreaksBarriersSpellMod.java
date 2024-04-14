package FrierenMod.cardMods;

import FrierenMod.cards.white.BreaksBarriersSpell;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class BreaksBarriersSpellMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(BreaksBarriersSpellMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;
    private final int currentLevel;
    private final int currentLevelRequiredNumber;
    private final int currentInLevelProgressNumber;

    public BreaksBarriersSpellMod(int currentLevel, int currentLevelRequiredNumber, int currentInLevelProgressNumber) {
        this.currentLevel = currentLevel;
        this.currentLevelRequiredNumber = currentLevelRequiredNumber;
        this.currentInLevelProgressNumber = currentInLevelProgressNumber;
    }

    public AbstractCardModifier makeCopy() {
        return new BreaksBarriersSpellMod(this.currentLevel,this.currentLevelRequiredNumber,this.currentInLevelProgressNumber);
    }

    public void onInitialApplication(AbstractCard card) {
        if (card instanceof BreaksBarriersSpell)
            switch (this.currentLevel){
                case 3:
                    ((BreaksBarriersSpell)card).loadCardImage(ModInformation.makeCardImgPath("BreaksBarriersSpell1"));
                    break;
                case 2:
                    ((BreaksBarriersSpell)card).loadCardImage(ModInformation.makeCardImgPath("BreaksBarriersSpell2"));
                    break;
                case 1:
                    ((BreaksBarriersSpell)card).loadCardImage(ModInformation.makeCardImgPath("BreaksBarriersSpell3"));
                    break;
                default:
                    break;
            }

    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        rawDescription = "";
        String taskString;
        String rewardSting;
        switch (currentLevel) {
            case 3:
                taskString = TEXT[3];
                rewardSting = String.format(TEXT[4], card.magicNumber);
                break;
            case 2:
                taskString = TEXT[5];
                rewardSting = String.format(TEXT[6], card.magicNumber);
                break;
            case 1:
                taskString = TEXT[7];
                rewardSting = String.format(TEXT[8], card.damage);
                break;
            default:
                taskString = "Error";
                rewardSting = "Error";
                break;
        }
        return rawDescription + generateDescription(taskString,rewardSting);
    }
    private String generateDescription(String taskString, String AwardString){
        return String.format(TEXT[0], currentLevel) + taskString + TEXT[1] + AwardString + String.format(TEXT[2], currentInLevelProgressNumber,currentLevelRequiredNumber);
    }
}
