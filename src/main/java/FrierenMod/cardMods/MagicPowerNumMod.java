package FrierenMod.cardMods;
import FrierenMod.actions.MakeMagicPowerInDiscardAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class MagicPowerNumMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(MagicPowerNumMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private final int magicPowerAmt;

    public MagicPowerNumMod(int magicPowerAmt) {
        this.magicPowerAmt = magicPowerAmt;
    }
    public AbstractCardModifier makeCopy() {
        return new MagicPowerNumMod(this.magicPowerAmt);
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        this.addToBot(new MakeMagicPowerInDiscardAction(magicPowerAmt));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + TEXT[0] + this.magicPowerAmt + TEXT[1];
    }
}