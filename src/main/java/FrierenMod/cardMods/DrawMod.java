package FrierenMod.cardMods;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
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

    public AbstractCardModifier makeCopy() {
        return new DrawMod(this.drawAmt);
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DrawCardAction(this.drawAmt));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        if (this.drawAmt == 1)
            return rawDescription + TEXT[0] + this.drawAmt + TEXT[1];
        return rawDescription + TEXT[0] + this.drawAmt + TEXT[2];
    }
}