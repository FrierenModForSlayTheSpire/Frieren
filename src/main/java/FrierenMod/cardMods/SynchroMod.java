package FrierenMod.cardMods;

import FrierenMod.actions.SynchroAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SynchroMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(SynchroMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    public SynchroMod() {
    }

    public AbstractCardModifier makeCopy() {
        return new SynchroMod();
    }

    public void onInitialApplication(AbstractCard card) {
        if (card.hasTag(AbstractBaseCard.Enum.ACCEL_SYNCHRO) && card.hasTag(AbstractBaseCard.Enum.LIMIT_OVER_SYNCHRO)) {
            card.exhaust = true;
            if (card instanceof Mana) {
                card.target = AbstractCard.CardTarget.ALL_ENEMY;
                ((CustomCard) card).loadCardImage(ModInformation.makeCardImgPath("Mana4"));
            }
        }
        else if (card.hasTag(AbstractBaseCard.Enum.ACCEL_SYNCHRO)) {
            card.exhaust = true;
            if (card instanceof Mana) {
                card.target = AbstractCard.CardTarget.NONE;
                ((CustomCard) card).loadCardImage(ModInformation.makeCardImgPath("Mana2"));
            }
        }
        else if (card.hasTag(AbstractBaseCard.Enum.LIMIT_OVER_SYNCHRO)) {
            card.exhaust = true;
            if (card instanceof Mana) {
                card.target = AbstractCard.CardTarget.ALL_ENEMY;
                ((CustomCard) card).loadCardImage(ModInformation.makeCardImgPath("Mana3"));
            }
        }
        else if (card.hasTag(AbstractBaseCard.Enum.SYNCHRO)) {
            card.exhaust = true;
            if (card instanceof Mana) {
                card.target = AbstractCard.CardTarget.NONE;
                ((CustomCard) card).loadCardImage(ModInformation.makeCardImgPath("Mana"));
            }
        }
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom(new SynchroAction(card));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        String keyword = "";
        if (card.hasTag(AbstractBaseCard.Enum.ACCEL_SYNCHRO) && card.hasTag(AbstractBaseCard.Enum.LIMIT_OVER_SYNCHRO)) {
            keyword = TEXT[3];
        }
        else if (card.hasTag(AbstractBaseCard.Enum.ACCEL_SYNCHRO)) {
            keyword = TEXT[1];
        }
        else if (card.hasTag(AbstractBaseCard.Enum.LIMIT_OVER_SYNCHRO)) {
            keyword = TEXT[2];
        }
        else if (card.hasTag(AbstractBaseCard.Enum.SYNCHRO)) {
            keyword = TEXT[0];
        }
        if (containsKeyword(rawDescription)) {
            return replaceKeyword(rawDescription, keyword);
        } else
            return rawDescription + " NL " + keyword + TEXT[4];
    }

    private String replaceKeyword(String rawDescription, String newKeyword) {
        return rawDescription.replaceAll(TEXT[0], newKeyword).replaceAll(TEXT[1], newKeyword).replaceAll(TEXT[2], newKeyword).replaceAll(TEXT[3], newKeyword);
    }

    private Boolean containsKeyword(String rawDescription) {
        return rawDescription.contains(TEXT[0]) || rawDescription.contains(TEXT[1]) || rawDescription.contains(TEXT[2]) || rawDescription.contains(TEXT[3]);
    }
}
