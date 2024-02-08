package FrierenMod.cardMods;

import FrierenMod.helpers.ModInfo;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class CardDescriptionBackMod extends AbstractCardModifier {
    public static final String ID = ModInfo.makeID(CardDescriptionBackMod.class.getSimpleName());

    public static final String[] TEXT = null;

    public CardDescriptionBackMod() {
    }

    public AbstractCardModifier makeCopy() {
        return new CardDescriptionBackMod();
    }

    public void onInitialApplication(AbstractCard card) {
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return CardCrawlGame.languagePack.getCardStrings(card.cardID).DESCRIPTION;
    }
}
