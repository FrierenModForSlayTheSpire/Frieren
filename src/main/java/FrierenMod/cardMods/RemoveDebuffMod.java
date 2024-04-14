package FrierenMod.cardMods;

import FrierenMod.cards.tempCards.CustomLegendarySpell;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RemoveDebuffMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(RemoveDebuffMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;


    public RemoveDebuffMod() {
    }
    public void onInitialApplication(AbstractCard card) {
        if(card instanceof CustomLegendarySpell)
            ((CustomLegendarySpell) card).usedModifierText += TEXT[0];
    }

    public AbstractCardModifier makeCopy() {
        return new RemoveDebuffMod();
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RemoveDebuffsAction(AbstractDungeon.player));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

//    public String modifyDescription(String rawDescription, AbstractCard card) {
//        return rawDescription + TEXT[0];
//    }
}