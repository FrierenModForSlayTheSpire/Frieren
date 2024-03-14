package FrierenMod.cardMods;

import FrierenMod.cards.tempCards.CustomLegendaryMagic;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ScryMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(ScryMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private final int scryAmt;

    public ScryMod(int scryAmt) {
        this.scryAmt = scryAmt;
    }
    public void onInitialApplication(AbstractCard card) {
        if(card instanceof CustomLegendaryMagic)
            ((CustomLegendaryMagic) card).usedModifierText += TEXT[0] + this.scryAmt + TEXT[1];
    }

    public AbstractCardModifier makeCopy() {
        return new ScryMod(this.scryAmt);
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ScryAction(this.scryAmt));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

//    public String modifyDescription(String rawDescription, AbstractCard card) {
//        return rawDescription + TEXT[0] + this.scryAmt + TEXT[1];
//    }
}