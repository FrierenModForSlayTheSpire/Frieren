package FrierenMod.cardMods;

import FrierenMod.cards.canAutoAdd.tempCards.CustomLegendaryMagic;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BlockMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(BlockMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private final int blockAmt;

    public BlockMod(int blockAmt) {
        this.blockAmt = blockAmt;
        this.priority = -2;
    }

    public AbstractCardModifier makeCopy() {
        return new BlockMod(this.blockAmt);
    }

    public void onInitialApplication(AbstractCard card) {
        card.block = card.baseBlock = this.blockAmt;
        if(card instanceof CustomLegendaryMagic)
            ((CustomLegendaryMagic) card).usedModifierText += TEXT[0] + "!B!" + TEXT[1];
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, card.block));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

//    public String modifyDescription(String rawDescription, AbstractCard card) {
//        return rawDescription + TEXT[0] + card.block + TEXT[1];
//    }
}
