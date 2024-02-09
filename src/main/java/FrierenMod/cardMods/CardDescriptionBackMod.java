package FrierenMod.cardMods;

import FrierenMod.helpers.ModInfo;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class CardDescriptionBackMod extends AbstractCardModifier {
    public static final String ID = ModInfo.makeID(CardDescriptionBackMod.class.getSimpleName());

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
        String upgradeDescription = CardCrawlGame.languagePack.getCardStrings(card.cardID).UPGRADE_DESCRIPTION;
        if(card.upgraded && upgradeDescription != null){
            return upgradeDescription;
        }
        return CardCrawlGame.languagePack.getCardStrings(card.cardID).DESCRIPTION;
    }
}
