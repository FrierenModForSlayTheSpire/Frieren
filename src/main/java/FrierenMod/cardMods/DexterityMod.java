package FrierenMod.cardMods;

import FrierenMod.helpers.ModHelper;
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
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class DexterityMod extends AbstractCardModifier {
    public static final String ID = ModHelper.makePath(DexterityMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private int dexterityAmt;

    public DexterityMod(int dexterityAmt) {
        this.dexterityAmt = dexterityAmt;
    }

    public AbstractCardModifier makeCopy() {
        return new DexterityMod(this.dexterityAmt);
    }

    public void onInitialApplication(AbstractCard card) {
        if(!card.exhaust){
            card.exhaust = true;
        }
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new DexterityPower(AbstractDungeon.player, this.dexterityAmt), this.dexterityAmt));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + TEXT[2] + this.dexterityAmt + TEXT[3];
    }
}