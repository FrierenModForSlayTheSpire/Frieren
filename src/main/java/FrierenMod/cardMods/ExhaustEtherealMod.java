package FrierenMod.cardMods;


import FrierenMod.cards.tempCards.SpecializedOffensiveMagic;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class ExhaustEtherealMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(ExhaustEtherealMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;


    public ExhaustEtherealMod() {
    }

    public void onInitialApplication(AbstractCard card) {
        card.exhaust = true;
        card.isEthereal = true;
        if (card instanceof SpecializedOffensiveMagic)
            ((SpecializedOffensiveMagic) card).usedModifierText += TEXT[0];
    }

    public AbstractCardModifier makeCopy() {
        return new ExhaustEtherealMod();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

}