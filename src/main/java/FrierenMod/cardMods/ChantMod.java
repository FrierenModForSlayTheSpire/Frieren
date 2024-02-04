package FrierenMod.cardMods;

import FrierenMod.actions.ChantAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.helpers.ModHelper;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ChantMod extends AbstractCardModifier {
    public static final String ID = ModHelper.makePath(ChantMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private final int chantAmt;

    public ChantMod(int chantAmt) {
        this.chantAmt = chantAmt;
    }
    public void onInitialApplication(AbstractCard card) {
        card.magicNumber = this.chantAmt;
        ((AbstractFrierenCard) card).isChantCard = true;
    }

    public AbstractCardModifier makeCopy() {
        return new ChantMod(this.chantAmt);
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChantAction(this.chantAmt));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + TEXT[2] + this.chantAmt + TEXT[3];
    }
}