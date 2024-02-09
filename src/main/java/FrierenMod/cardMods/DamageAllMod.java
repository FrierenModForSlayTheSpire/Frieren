package FrierenMod.cardMods;

import FrierenMod.cards.tempCards.CustomLegendMagic;
import FrierenMod.helpers.ModInfo;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DamageAllMod extends AbstractCardModifier {
    public static final String ID = ModInfo.makeID(DamageAllMod.class.getSimpleName());

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(ID)).TEXT;

    private int damageAmt;

    public DamageAllMod(int damageAmt) {
        this.damageAmt = damageAmt;
        this.priority = -1;
    }

    public AbstractCardModifier makeCopy() {
        return new DamageAllMod(this.damageAmt);
    }

    public void onInitialApplication(AbstractCard card) {
        card.baseDamage = this.damageAmt;
        card.target = AbstractCard.CardTarget.ALL_ENEMY;
        card.type = AbstractCard.CardType.ATTACK;
        if (card instanceof CustomLegendMagic)
            ((CustomLegendMagic)card).loadCardImage("FrierenModResources/img/cards/CustomLegendMagic_attack.png");
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(card.baseDamage, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE, true));
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + TEXT[2] + card.baseDamage + TEXT[3];
    }
}
