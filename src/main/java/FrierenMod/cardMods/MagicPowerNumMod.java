package FrierenMod.cardMods;
import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.helpers.ModInfo;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MagicPowerNumMod extends AbstractCardModifier {
    public static final String ID = ModInfo.makeID(MagicPowerNumMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private final int magicPowerAmt;

    public MagicPowerNumMod(int magicPowerAmt) {
        this.magicPowerAmt = magicPowerAmt;
    }

    public AbstractCardModifier makeCopy() {
        return new MagicPowerNumMod(this.magicPowerAmt);
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInDiscardAction(new MagicPower(),this.magicPowerAmt));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + TEXT[2] + this.magicPowerAmt + TEXT[4];
    }
}