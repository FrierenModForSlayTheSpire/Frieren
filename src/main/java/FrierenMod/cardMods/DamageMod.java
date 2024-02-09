package FrierenMod.cardMods;

import FrierenMod.cards.tempCards.CustomLegendMagic;
import FrierenMod.helpers.ModInfo;
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
    public static final String ID = ModInfo.makeID(DamageMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private final int damageAmt;

    public DamageMod(int damageAmt) {
        this.damageAmt = damageAmt;
        this.priority = -1;
    }

    public AbstractCardModifier makeCopy() {
        return new DamageMod(this.damageAmt);
    }

    public void onInitialApplication(AbstractCard card) {
        card.baseDamage = this.damageAmt;
        card.target = AbstractCard.CardTarget.ENEMY;
        card.type = AbstractCard.CardType.ATTACK;
        if (card instanceof CustomLegendMagic)
            ((CustomLegendMagic)card).loadCardImage("FrierenModResources/img/cards/CustomLegendMagic_attack.png");
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(target, new DamageInfo((AbstractCreature)AbstractDungeon.player, card.damage, card.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + TEXT[2] + card.baseDamage + TEXT[3];
    }
}