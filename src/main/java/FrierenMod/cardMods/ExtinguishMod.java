package FrierenMod.cardMods;

import FrierenMod.helpers.ModInfo;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ExtinguishMod extends AbstractCardModifier {
    public static final String ID = ModInfo.makeID(ExtinguishMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private int hpAmt;

    public ExtinguishMod(int hpAmt) {
        this.hpAmt = hpAmt;
    }

    public AbstractCardModifier makeCopy() {
        return new ExtinguishMod(this.hpAmt);
    }

    public void onInitialApplication(AbstractCard card) {
        card.target = AbstractCard.CardTarget.ENEMY;
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new JudgementAction(target,this.hpAmt));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + TEXT[2] + this.hpAmt + TEXT[3];
    }
}
