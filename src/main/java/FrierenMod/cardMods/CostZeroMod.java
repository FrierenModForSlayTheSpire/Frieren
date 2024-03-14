package FrierenMod.cardMods;

import FrierenMod.actions.RandomCostZeroForTurnAction;
import FrierenMod.cards.tempCards.CustomLegendaryMagic;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CostZeroMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(CostZeroMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private final int cardAmt;

    public CostZeroMod(int cardAmt) {
        this.cardAmt = cardAmt;
    }
    public void onInitialApplication(AbstractCard card) {
        if(card instanceof CustomLegendaryMagic){
            if(this.cardAmt == 1)
                ((CustomLegendaryMagic) card).usedModifierText += TEXT[0] + this.cardAmt + TEXT[1];
            else
                ((CustomLegendaryMagic) card).usedModifierText += TEXT[0] + this.cardAmt + TEXT[2];
        }
    }

    public AbstractCardModifier makeCopy() {
        return new CostZeroMod(this.cardAmt);
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        for (int i = 0; i < this.cardAmt; i++) {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RandomCostZeroForTurnAction());
        }
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

//    public String modifyDescription(String rawDescription, AbstractCard card) {
//        if(this.cardAmt == 1)
//            return rawDescription + TEXT[0] + this.cardAmt + TEXT[1];
//        return rawDescription + TEXT[0] + this.cardAmt + TEXT[2];
//    }
}