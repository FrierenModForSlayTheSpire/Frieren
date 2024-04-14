package FrierenMod.cardMods;

import FrierenMod.cards.tempCards.CustomLegendarySpell;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class DexterityMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(DexterityMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private final int dexterityAmt;

    public DexterityMod(int dexterityAmt) {
        this.dexterityAmt = dexterityAmt;
    }

    public AbstractCardModifier makeCopy() {
        return new DexterityMod(this.dexterityAmt);
    }

    public void onInitialApplication(AbstractCard card) {
        if(card instanceof CustomLegendarySpell)
            ((CustomLegendarySpell) card).usedModifierText += TEXT[0] + this.dexterityAmt + TEXT[1];
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new DexterityPower(AbstractDungeon.player, this.dexterityAmt), this.dexterityAmt));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

//    public String modifyDescription(String rawDescription, AbstractCard card) {
//        return rawDescription + TEXT[0] + this.dexterityAmt + TEXT[1];
//    }
}