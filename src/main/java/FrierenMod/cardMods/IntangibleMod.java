package FrierenMod.cardMods;

import FrierenMod.cards.canAutoAdd.tempCards.CustomLegendaryMagic;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

public class IntangibleMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(IntangibleMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private final int stackAmt;

    public IntangibleMod(int stackAmt) {
        this.stackAmt = stackAmt;
    }
    public void onInitialApplication(AbstractCard card) {
        if(!card.exhaust){
            card.exhaust = true;
        }
        if(card instanceof CustomLegendaryMagic)
            ((CustomLegendaryMagic) card).usedModifierText += TEXT[0] + this.stackAmt + TEXT[1];
    }

    public AbstractCardModifier makeCopy() {
        return new IntangibleMod(this.stackAmt);
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new IntangiblePlayerPower(AbstractDungeon.player, this.stackAmt), this.stackAmt));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

//    public String modifyDescription(String rawDescription, AbstractCard card) {
//        return rawDescription + TEXT[0] + this.stackAmt + TEXT[1];
//    }
}