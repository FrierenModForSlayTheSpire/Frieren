package FrierenMod.cardMods;
import FrierenMod.actions.MakeManaInDiscardAction;
import FrierenMod.cards.canAutoAdd.tempCards.CustomLegendarySpell;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class ManaNumMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(ManaNumMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private final int manaAmt;

    public ManaNumMod(int manaAmt) {
        this.manaAmt = manaAmt;
    }
    public void onInitialApplication(AbstractCard card) {
        if(card instanceof CustomLegendarySpell)
            ((CustomLegendarySpell) card).usedModifierText += TEXT[0] + this.manaAmt + TEXT[1];
    }
    public AbstractCardModifier makeCopy() {
        return new ManaNumMod(this.manaAmt);
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        this.addToBot(new MakeManaInDiscardAction(manaAmt));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

//    public String modifyDescription(String rawDescription, AbstractCard card) {
//        return rawDescription + TEXT[0] + this.manaAmt + TEXT[1];
//    }
}