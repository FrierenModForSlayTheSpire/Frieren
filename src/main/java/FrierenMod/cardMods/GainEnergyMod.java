package FrierenMod.cardMods;

import FrierenMod.cards.tempCards.SpecializedOffensiveMagic;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class GainEnergyMod extends AbstractCardModifier implements SpecializedOffensiveMagicMod{
    public static final String ID = ModInformation.makeID(GainEnergyMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private int amount;

    public GainEnergyMod(int amount) {
        this.amount = amount;
        this.priority = -1;
    }

    public AbstractCardModifier makeCopy() {
        return new GainEnergyMod(this.amount);
    }

    public void onInitialApplication(AbstractCard card) {
        if (card instanceof SpecializedOffensiveMagic) {
            ((SpecializedOffensiveMagic) card).usedModifierText += TEXT[0] + this.amount + TEXT[1];
        }
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.amount));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public void updateAmt(int newAmt) {
        this.amount = newAmt;
    }

//    public String modifyDescription(String rawDescription, AbstractCard card) {
//        return rawDescription + TEXT[0] + card.damage + TEXT[1];
//    }
}