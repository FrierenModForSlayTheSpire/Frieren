package FrierenMod.actions;

import FrierenMod.cards.AbstractBaseCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ReplaceManaAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final int amt;

    public ReplaceManaAction(AbstractPlayer p, int amt) {
        this.p = p;
        this.amt = amt;
    }

    @Override
    public void update() {
        int i = 0;
        for (AbstractCard c : p.drawPile.group) {
            if (c.hasTag(AbstractBaseCard.Enum.MANA)) {
                if (i >= amt) {
                    this.isDone = true;
                    return;
                }
                this.addToBot(new DestroySpecifiedCardAction(c, p.drawPile));
                this.addToBot(new MakeTempCardInDrawPileAction(getDebuffCard(), 1, true, true));
                i++;
            }
        }
        for (AbstractCard c : p.discardPile.group) {
            if (c.hasTag(AbstractBaseCard.Enum.MANA)) {
                if (i >= amt) {
                    this.isDone = true;
                    return;
                }
                this.addToBot(new DestroySpecifiedCardAction(c, p.discardPile));
                this.addToBot(new MakeTempCardInDiscardAction(getDebuffCard(), 1));
                i++;
            }
        }
        this.isDone = true;
    }

    private AbstractCard getDebuffCard() {
        int rng = AbstractDungeon.aiRng.random(99);
        AbstractCard retVal;
        switch (rng % 5) {
            case 1:
                retVal = new Slimed();
                break;
            case 2:
                retVal = new Wound();
                break;
            case 3:
                retVal = new Burn();
                break;
            case 4:
                retVal = new VoidCard();
                break;
            default:
                retVal = new Dazed();
                break;
        }
        return retVal;
    }
}
