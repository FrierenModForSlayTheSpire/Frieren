package FrierenMod.cardMods;

import FrierenMod.cards.tempCards.CustomLegendaryMagic;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ExtinguishMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(ExtinguishMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private final int hpAmt;

    public ExtinguishMod(int hpAmt) {
        this.hpAmt = hpAmt;
    }

    public AbstractCardModifier makeCopy() {
        return new ExtinguishMod(this.hpAmt);
    }

    public void onInitialApplication(AbstractCard card) {
        card.target = AbstractCard.CardTarget.ENEMY;
        if(card instanceof CustomLegendaryMagic)
            ((CustomLegendaryMagic) card).usedModifierText += TEXT[0] + this.hpAmt + TEXT[1];
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new JudgementAction(target,this.hpAmt));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

//    public String modifyDescription(String rawDescription, AbstractCard card) {
//        return rawDescription + TEXT[0] + this.hpAmt + TEXT[1];
//    }
}
