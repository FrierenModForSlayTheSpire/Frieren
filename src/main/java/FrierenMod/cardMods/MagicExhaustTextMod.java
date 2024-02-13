package FrierenMod.cardMods;

import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class MagicExhaustTextMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(MagicExhaustTextMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private final int magicExhaustAmt;

    public MagicExhaustTextMod(int magicExhaustAmt) {
        this.magicExhaustAmt = magicExhaustAmt;
    }

    public AbstractCardModifier makeCopy() {
        return new MagicExhaustTextMod(magicExhaustAmt);
    }

    public void onInitialApplication(AbstractCard card) {
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return String.format(TEXT[0],this.magicExhaustAmt) + rawDescription;
    }
}
