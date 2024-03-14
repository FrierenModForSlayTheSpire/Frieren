package FrierenMod.cardMods;

import FrierenMod.cards.tempCards.CustomLegendaryMagic;
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

public class DamageThirdMod extends AbstractCardModifier {
    public static final String ID = ModInformation.makeID(DamageThirdMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private final int damageAmt;

    public DamageThirdMod(int damageAmt) {
        this.damageAmt = damageAmt;
        this.priority = -1;
    }

    public AbstractCardModifier makeCopy() {
        return new DamageThirdMod(this.damageAmt);
    }

    public void onInitialApplication(AbstractCard card) {
        card.damage = card.baseDamage = this.damageAmt;
        card.target = AbstractCard.CardTarget.ENEMY;
        card.type = AbstractCard.CardType.ATTACK;
        if (card instanceof CustomLegendaryMagic){
            ((CustomLegendaryMagic)card).loadCardImage("FrierenModResources/img/cards/CustomLegendaryMagic (2).png");
            ((CustomLegendaryMagic) card).usedModifierText += TEXT[0] + "!D!" + TEXT[1];
        }
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        for (int i = 0; i < 3; i++) {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(target, new DamageInfo((AbstractCreature)AbstractDungeon.player, card.damage, card.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        }
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

//    public String modifyDescription(String rawDescription, AbstractCard card) {
//        return rawDescription + TEXT[0] + card.damage + TEXT[1];
//    }
}