package FrierenMod.cardMods;

import FrierenMod.helpers.ModInfo;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class GainRandomCardMod extends AbstractCardModifier {
    public static final String ID = ModInfo.makeID(GainRandomCardMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private int cardAmt;

    public GainRandomCardMod(int cardAmt) {
        this.cardAmt = cardAmt;
    }

    public AbstractCardModifier makeCopy() {
        return new GainRandomCardMod(this.cardAmt);
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        for (int i = 0; i < cardAmt; i++) {
            AbstractCard c = AbstractDungeon.getCard(AbstractCard.CardRarity.COMMON, AbstractDungeon.cardRandomRng).makeCopy();
            c.costForTurn = 0;
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInHandAction(c, 1, false));
        }
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        if (this.cardAmt == 1)
            return rawDescription + TEXT[2] + this.cardAmt + TEXT[3];
        return rawDescription + TEXT[2] + this.cardAmt + TEXT[4];
    }
}