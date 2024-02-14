package FrierenMod.cardMods;

import FrierenMod.cards.white.AnalysisMagic;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class AnalysisMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(AnalysisMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;
    private final int currentLevel;
    private final int currentLevelRequiredNumber;
    private final int currentInLevelProgressNumber;

    public AnalysisMod(int currentLevel, int currentLevelRequiredNumber, int currentInLevelProgressNumber) {
        this.currentLevel = currentLevel;
        this.currentLevelRequiredNumber = currentLevelRequiredNumber;
        this.currentInLevelProgressNumber = currentInLevelProgressNumber;
    }

    public AbstractCardModifier makeCopy() {
        return new AnalysisMod(this.currentLevel,this.currentLevelRequiredNumber,this.currentInLevelProgressNumber);
    }

    public void onInitialApplication(AbstractCard card) {
        if (card instanceof AnalysisMagic)
            switch (this.currentLevel){
                case 3:
                    ((AnalysisMagic)card).loadCardImage(ModInformation.makeCardImgPath("AnalysisMagic1"));
                    break;
                case 2:
                    ((AnalysisMagic)card).loadCardImage(ModInformation.makeCardImgPath("AnalysisMagic2"));
                    break;
                case 1:
                    ((AnalysisMagic)card).loadCardImage(ModInformation.makeCardImgPath("AnalysisMagic3"));
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
