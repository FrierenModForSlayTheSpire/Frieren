package FrierenMod.cardMods;

import FrierenMod.actions.MakeManaInDrawPileAction;
import FrierenMod.cards.tempCards.CustomLegendaryMagic;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class ManaInDrawNumMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(ManaInDrawNumMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private final int manaAmt;

    public ManaInDrawNumMod(int manaAmt) {
        this.manaAmt = manaAmt;
    }
    public void onInitialApplication(AbstractCard card) {
        if(card instanceof CustomLegendaryMagic)
            ((CustomLegendaryMagic) card).usedModifierText += TEXT[0] + this.manaAmt + TEXT[1];
    }
    public AbstractCardModifier makeCopy() {
        return new ManaInDrawNumMod(this.manaAmt);
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        this.addToBot(new MakeManaInDrawPileAction(manaAmt));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

//    public String modifyDescription(String rawDescription, AbstractCard card) {
//        return rawDescription + TEXT[0] + this.manaAmt + TEXT[1];
//    }
}