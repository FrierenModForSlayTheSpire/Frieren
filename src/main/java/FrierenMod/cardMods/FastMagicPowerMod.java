package FrierenMod.cardMods;

import FrierenMod.actions.AbstractFrierenAction;
import FrierenMod.actions.MagicPowerAction;
import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.helpers.ModInfo;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static FrierenMod.tags.CustomTags.FAST_MAGIC_POWER;
import static FrierenMod.tags.CustomTags.FINAL_MAGIC_POWER;

public class FastMagicPowerMod extends AbstractCardModifier {
    public static final String ID = ModInfo.makeID(FastMagicPowerMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    public FastMagicPowerMod() {
    }

    public AbstractCardModifier makeCopy() {
        return new FastMagicPowerMod();
    }

    public void onInitialApplication(AbstractCard card) {
        card.tags.add(FAST_MAGIC_POWER);
        if(!card.tags.contains(FINAL_MAGIC_POWER)){
            card.target = AbstractCard.CardTarget.NONE;
            card.type = AbstractCard.CardType.SKILL;
        }
        card.exhaust = true;
        if (card instanceof MagicPower)
            ((MagicPower)card).loadCardImage("FrierenModResources/img/cards/MagicPower2.png");
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.actions.removeIf(action1 -> action1 instanceof AbstractFrierenAction && ((AbstractFrierenAction) action1).isMagicPowerAction);
        this.addToBot(new MagicPowerAction(2));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return TEXT[0];
    }
}