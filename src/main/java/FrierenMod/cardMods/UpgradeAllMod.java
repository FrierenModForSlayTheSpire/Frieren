package FrierenMod.cardMods;


import FrierenMod.cards.tempCards.CustomLegendaryMagic;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.ApotheosisAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class UpgradeAllMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(UpgradeAllMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;


    public UpgradeAllMod() {
    }

    public void onInitialApplication(AbstractCard card) {
        if(!card.exhaust){
            card.exhaust = true;
        }
        if(card instanceof CustomLegendaryMagic)
            ((CustomLegendaryMagic) card).usedModifierText += TEXT[0];
    }
    public AbstractCardModifier makeCopy() {
        return new UpgradeAllMod();
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApotheosisAction());
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

//    public String modifyDescription(String rawDescription, AbstractCard card) {
//        return rawDescription + TEXT[0];
//    }
}