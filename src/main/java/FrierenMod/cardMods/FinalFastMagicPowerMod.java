package FrierenMod.cardMods;

import FrierenMod.actions.AbstractFrierenAction;
import FrierenMod.actions.MagicPowerAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.helpers.ModInfo;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FinalFastMagicPowerMod extends AbstractCardModifier {
    public static final String ID = ModInfo.makeID(FinalFastMagicPowerMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    public FinalFastMagicPowerMod() {
    }

    public AbstractCardModifier makeCopy() {
        return new FinalFastMagicPowerMod();
    }

    public void onInitialApplication(AbstractCard card) {
        ((AbstractFrierenCard)card).isFastMagicPower = true;
        ((AbstractFrierenCard)card).isFinalMagicPower = true;
        card.baseDamage = 20;
        card.target = AbstractCard.CardTarget.ALL_ENEMY;
        card.type = AbstractCard.CardType.ATTACK;
        card.exhaust = true;
        if (card instanceof MagicPower)
            ((MagicPower)card).loadCardImage("FrierenModResources/img/cards/Strike.png");
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.actions.removeIf(action1 -> action1 instanceof AbstractFrierenAction && ((AbstractFrierenAction) action1).isMagicPowerAction);
        this.addToBot(new MagicPowerAction(card,4));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return TEXT[0];
    }
}