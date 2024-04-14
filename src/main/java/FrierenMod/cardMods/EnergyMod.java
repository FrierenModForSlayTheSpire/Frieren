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
import com.megacrit.cardcrawl.powers.EnergizedPower;

public class EnergyMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(EnergyMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private final int energyAmt;

    public EnergyMod(int energyAmt) {
        this.energyAmt = energyAmt;
    }
    public void onInitialApplication(AbstractCard card) {
        if(card instanceof CustomLegendarySpell)
            ((CustomLegendarySpell) card).usedModifierText += TEXT[0] + TEXT[this.energyAmt] + TEXT[4];
    }

    public AbstractCardModifier makeCopy() {
        return new EnergyMod(this.energyAmt);
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new EnergizedPower(AbstractDungeon.player, this.energyAmt), this.energyAmt));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

//    public String modifyDescription(String rawDescription, AbstractCard card) {
//        return rawDescription + TEXT[0] + TEXT[this.energyAmt] + TEXT[4];
//    }
}