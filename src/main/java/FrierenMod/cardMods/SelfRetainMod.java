package FrierenMod.cardMods;

import FrierenMod.cards.canAutoAdd.tempCards.CustomLegendarySpell;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SelfRetainMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(SelfRetainMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private final int selfRetainAmt;

    public SelfRetainMod(int selfRetainAmt) {
        this.selfRetainAmt = selfRetainAmt;
    }
    public void onInitialApplication(AbstractCard card) {
        if(card instanceof CustomLegendarySpell){
            if(this.selfRetainAmt == 1)
                ((CustomLegendarySpell) card).usedModifierText += TEXT[0] + this.selfRetainAmt + TEXT[1];
            else
                ((CustomLegendarySpell) card).usedModifierText += TEXT[0] + this.selfRetainAmt + TEXT[2];
        }
    }

    public AbstractCardModifier makeCopy() {
        return new SelfRetainMod(this.selfRetainAmt);
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RetainCardsAction(AbstractDungeon.player,this.selfRetainAmt));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

//    public String modifyDescription(String rawDescription, AbstractCard card) {
//        if(this.selfRetainAmt == 1)
//            return rawDescription + TEXT[0] + this.selfRetainAmt + TEXT[1];
//        return rawDescription + TEXT[0] + this.selfRetainAmt + TEXT[2];
//    }
}