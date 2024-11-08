package FrierenMod.cardMods;

import FrierenMod.cards.tempCards.SpecializedOffensiveMagic;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class StrengthMod extends AbstractCardModifier implements SpecializedOffensiveMagicMod{
    public static final String ID = ModInformation.makeID(StrengthMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;
    private int amount;

    public StrengthMod(int amount) {
        this.amount = amount;
    }

    public void onInitialApplication(AbstractCard card) {
        if (card instanceof SpecializedOffensiveMagic) {
            ((SpecializedOffensiveMagic) card).usedModifierText += TEXT[0] + amount + TEXT[1];
        }

    }

    public AbstractCardModifier makeCopy() {
        return new StrengthMod(this.amount);
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, amount)));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public void updateAmt(int newAmt) {
        this.amount = newAmt;
    }
}
