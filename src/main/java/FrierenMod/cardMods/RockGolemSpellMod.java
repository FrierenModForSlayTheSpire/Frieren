//package FrierenMod.cardMods;
//
//import FrierenMod.cards.white.RockGolemSpell;
//import FrierenMod.utils.ModInformation;
//import basemod.abstracts.AbstractCardModifier;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//
//@Deprecated
//public class RockGolemSpellMod extends AbstractCardModifier {
//    public static final String ID = ModInformation.makeID(RockGolemSpellMod.class.getSimpleName());
//
//    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;
//    private final int currentLevel;
//    private final int currentLevelRequiredNumber;
//    private final int currentInLevelProgressNumber;
//
//    public RockGolemSpellMod(int currentLevel, int currentLevelRequiredNumber, int currentInLevelProgressNumber) {
//        this.currentLevel = currentLevel;
//        this.currentLevelRequiredNumber = currentLevelRequiredNumber;
//        this.currentInLevelProgressNumber = currentInLevelProgressNumber;
//    }
//
//    public AbstractCardModifier makeCopy() {
//        return new RockGolemSpellMod(this.currentLevel,this.currentLevelRequiredNumber,this.currentInLevelProgressNumber);
//    }
//
//    public void onInitialApplication(AbstractCard card) {
//        if (card instanceof RockGolemSpell){
//            if(this.currentLevel % 2 == 0){
//                ((RockGolemSpell)card).loadCardImage(ModInformation.makeCardImgPath("RockGolemSpell"));
//            }else {
//                ((RockGolemSpell)card).loadCardImage(ModInformation.makeCardImgPath("RockGolemSpell1"));
//            }
//        }
//    }
//    public String identifier(AbstractCard card) {
//        return ID;
//    }
//
//    public String modifyDescription(String rawDescription, AbstractCard card) {
//        String taskString;
//        String rewardSting;
//        if(this.currentLevel % 2 == 0){
//            taskString = TEXT[3];
//            rewardSting = String.format(TEXT[4], card.damage);
//        }else {
//            taskString = TEXT[5];
//            rewardSting = String.format(TEXT[6],card.block);
//        }
//        return generateDescription(taskString,rewardSting);
//    }
//    private String generateDescription(String taskString, String AwardString){
//        return String.format(TEXT[0], currentLevel) + taskString + TEXT[1] + AwardString + String.format(TEXT[2], currentInLevelProgressNumber,currentLevelRequiredNumber);
//    }
//}
