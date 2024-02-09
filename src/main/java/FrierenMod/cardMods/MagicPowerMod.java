package FrierenMod.cardMods;

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

import static FrierenMod.tags.CustomTags.FINAL_MAGIC_POWER;

public class MagicPowerMod extends AbstractCardModifier {
    public static final String ID = ModInfo.makeID(MagicPowerMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    public MagicPowerMod() {
    }

    public AbstractCardModifier makeCopy() {
        return new MagicPowerMod();
    }

    public void onInitialApplication(AbstractCard card) {
        card.target = AbstractCard.CardTarget.NONE;
        card.type = AbstractCard.CardType.SKILL;
        card.exhaust = true;
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.actions.clear();
        this.addToBot(new DrawCardAction(1));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return TEXT[0];
    }
}