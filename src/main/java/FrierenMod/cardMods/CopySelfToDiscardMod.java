package FrierenMod.cardMods;

import FrierenMod.cards.tempCards.SpecializedOffensiveMagic;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class CopySelfToDiscardMod extends AbstractCardModifier implements SpecializedOffensiveMagicMod {
    public static final String ID = ModInformation.makeID(CopySelfToDiscardMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;
    private int amount;

    public CopySelfToDiscardMod(int amount) {
        this.amount = amount;
    }

    public void onInitialApplication(AbstractCard card) {
        if (card instanceof SpecializedOffensiveMagic) {
            if (amount == 1)
                ((SpecializedOffensiveMagic) card).usedModifierText += TEXT[0] + amount + TEXT[1];
            else
                ((SpecializedOffensiveMagic) card).usedModifierText += TEXT[0] + amount + TEXT[2];
        }

    }

    public AbstractCardModifier makeCopy() {
        return new CopySelfToDiscardMod(this.amount);
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        this.addToBot(new MakeTempCardInDiscardAction(card, amount));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public void updateAmt(int newAmt) {
        this.amount = newAmt;
    }
}
