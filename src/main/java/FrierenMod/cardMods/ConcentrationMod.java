package FrierenMod.cardMods;

import FrierenMod.cards.tempCards.SpecializedOffensiveMagic;
import FrierenMod.powers.ConcentrationPower;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ConcentrationMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(ConcentrationMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;
    private final int amount;

    public ConcentrationMod(int amount) {
        this.amount = amount;
    }

    public void onInitialApplication(AbstractCard card) {
        if (card instanceof SpecializedOffensiveMagic) {
            ((SpecializedOffensiveMagic) card).usedModifierText += TEXT[0] + amount + TEXT[1];
        }

    }

    public AbstractCardModifier makeCopy() {
        return new ConcentrationMod(this.amount);
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ConcentrationPower(amount)));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

}
