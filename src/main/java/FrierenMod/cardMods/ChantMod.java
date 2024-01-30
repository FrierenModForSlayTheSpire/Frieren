package FrierenMod.cardMods;
import FrierenMod.actions.ChantAction;
import FrierenMod.helpers.ChantHelper;
import FrierenMod.helpers.LegendMagicHelper;
import FrierenMod.helpers.ModHelper;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ChantMod extends AbstractCardModifier {
    public static final String ID = ModHelper.makePath(ChantMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private int chantAmt;

    public ChantMod(int chantAmt) {
        this.chantAmt = chantAmt;
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

    @Override
    public boolean canPlayCard(AbstractCard card) {
        return new ChantHelper().canChantUse(card,(AbstractMonster) null,this.chantAmt) && new LegendMagicHelper().canLegendMagicUse(card,(AbstractMonster) null);
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        if (this.chantAmt == 1)
            return rawDescription + TEXT[2] + this.chantAmt + TEXT[3];
        return rawDescription + TEXT[2] + this.chantAmt + TEXT[4];
    }
}