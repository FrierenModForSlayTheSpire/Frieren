package FrierenMod.cardMods;

import FrierenMod.cards.tempCards.CustomLegendarySpell;
import FrierenMod.cards.tempCards.SpecializedOffensiveMagic;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DamageMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(DamageMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private final int damageAmt;
    private final boolean increased;

    public DamageMod(int damageAmt) {
        this.damageAmt = damageAmt;
        this.priority = -1;
        this.increased = false;
    }

    public AbstractCardModifier makeCopy() {
        return new DamageMod(this.damageAmt);
    }

    public void onInitialApplication(AbstractCard card) {
        card.damage = card.baseDamage = this.damageAmt;
        card.target = AbstractCard.CardTarget.ENEMY;
        card.type = AbstractCard.CardType.ATTACK;
        if (card instanceof CustomLegendarySpell) {
            ((CustomLegendarySpell) card).loadCardImage("FrierenModResources/img/cards/CustomLegendarySpell (2).png");
            ((CustomLegendarySpell) card).usedModifierText += TEXT[0] + "!D!" + TEXT[1];
        } else if (card instanceof SpecializedOffensiveMagic) {
            ((SpecializedOffensiveMagic) card).usedModifierText += TEXT[0] + "!D!" + TEXT[1];
        }
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(target, new DamageInfo(AbstractDungeon.player, card.damage, card.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

//    public String modifyDescription(String rawDescription, AbstractCard card) {
//        return rawDescription + TEXT[0] + card.damage + TEXT[1];
//    }
}