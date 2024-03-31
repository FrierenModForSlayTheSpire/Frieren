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
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class VulnerableMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(VulnerableMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;
    private final int stackAmt;

    public VulnerableMod(int stackAmt) {
        this.stackAmt = stackAmt;
    }
    public void onInitialApplication(AbstractCard card) {
        if(card instanceof CustomLegendaryMagic)
            ((CustomLegendaryMagic) card).usedModifierText += TEXT[0] + this.stackAmt + TEXT[1];
    }

    public AbstractCardModifier makeCopy() {
        return new VulnerableMod(this.stackAmt);
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        for (AbstractMonster mo :AbstractDungeon.getCurrRoom().monsters.monsters){
            this.addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new VulnerablePower(mo, this.stackAmt, false), 1, true, AbstractGameAction.AttackEffect.NONE));
        }
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

//    public String modifyDescription(String rawDescription, AbstractCard card) {
//        return rawDescription + TEXT[0] + this.stackAmt + TEXT[1];
//    }
}
