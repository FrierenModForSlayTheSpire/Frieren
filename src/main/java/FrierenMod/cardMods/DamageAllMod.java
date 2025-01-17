package FrierenMod.cardMods;

import FrierenMod.cards.tempCards.CustomLegendarySpell;
import FrierenMod.utils.ModInformation;
import basemod.ReflectionHacks;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DamageAllMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(DamageAllMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private final int damageAmt;

    public DamageAllMod(int damageAmt) {
        this.damageAmt = damageAmt;
        this.priority = -1;
    }

    public AbstractCardModifier makeCopy() {
        return new DamageAllMod(this.damageAmt);
    }

    public void onInitialApplication(AbstractCard card) {
        card.damage = card.baseDamage = this.damageAmt;
        card.target = AbstractCard.CardTarget.ALL_ENEMY;
        card.type = AbstractCard.CardType.ATTACK;
        ReflectionHacks.setPrivate(card,AbstractCard.class, "isMultiDamage", true);
        if (card instanceof CustomLegendarySpell){
            ((CustomLegendarySpell)card).loadCardImage("FrierenModResources/img/cards/CustomLegendarySpell (2).png");
            ((CustomLegendarySpell) card).usedModifierText += TEXT[0] + "!D!" + TEXT[1];
        }
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, card.multiDamage, card.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

//    public String modifyDescription(String rawDescription, AbstractCard card) {
//        return rawDescription + TEXT[0] + card.damage + TEXT[1];
//    }
}
