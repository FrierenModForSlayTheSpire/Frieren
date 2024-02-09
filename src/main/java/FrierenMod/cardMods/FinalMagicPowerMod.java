package FrierenMod.cardMods;

import FrierenMod.cards.tempCards.CustomLegendMagic;
import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.helpers.ModInfo;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static FrierenMod.tags.CustomTags.FINAL_MAGIC_POWER;

public class FinalMagicPowerMod extends AbstractCardModifier {
    public static final String ID = ModInfo.makeID(FinalMagicPowerMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    public FinalMagicPowerMod() {
        this.priority = -1;
    }

    public AbstractCardModifier makeCopy() {
        return new FinalMagicPowerMod();
    }

    public void onInitialApplication(AbstractCard card) {
        card.baseDamage = 20;
        card.target = AbstractCard.CardTarget.ALL_ENEMY;
        card.type = AbstractCard.CardType.ATTACK;
        card.tags.add(FINAL_MAGIC_POWER);
        card.exhaust = true;
        if (card instanceof MagicPower)
            ((MagicPower)card).loadCardImage("FrierenModResources/img/cards/Strike.png");
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.actions.clear();
        this.addToBot(new AttackDamageRandomEnemyAction(card, AbstractGameAction.AttackEffect.LIGHTNING));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return TEXT[0];
    }
}