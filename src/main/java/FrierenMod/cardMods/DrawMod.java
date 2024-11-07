package FrierenMod.cardMods;

import FrierenMod.cards.tempCards.CustomLegendarySpell;
import FrierenMod.cards.tempCards.SpecializedOffensiveMagic;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DrawMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(DrawMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private final int drawAmt;

    public DrawMod(int drawAmt) {
        this.drawAmt = drawAmt;
    }

    public void onInitialApplication(AbstractCard card) {
        if (this.drawAmt == 1) {
            if (card instanceof CustomLegendarySpell)
                ((CustomLegendarySpell) card).usedModifierText += TEXT[0] + this.drawAmt + TEXT[1];
            else if (card instanceof SpecializedOffensiveMagic)
                ((SpecializedOffensiveMagic) card).usedModifierText += TEXT[0] + this.drawAmt + TEXT[1];
        } else {
            if (card instanceof CustomLegendarySpell)
                ((CustomLegendarySpell) card).usedModifierText += TEXT[0] + this.drawAmt + TEXT[2];
            else if (card instanceof SpecializedOffensiveMagic)
                ((SpecializedOffensiveMagic) card).usedModifierText += TEXT[0] + this.drawAmt + TEXT[2];
        }
    }

    public AbstractCardModifier makeCopy() {
        return new DrawMod(this.drawAmt);
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.drawAmt));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}