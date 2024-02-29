package FrierenMod.powers;

import FrierenMod.actions.ExhaustManaInDiscardPileAction;
import FrierenMod.actions.ExhaustManaInDrawPileAction;
import FrierenMod.actions.ExhaustManaInHandAction;
import FrierenMod.gameHelpers.ChantHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class PajamasFormPower extends AbstractFrierenPower {
    public static final String POWER_ID = ModInformation.makeID(PajamasFormPower.class.getSimpleName());
    private int baseDamage;
    public PajamasFormPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
    }
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            this.updateDescription();
            this.flash();
            this.addToBot(new ExhaustManaInDrawPileAction(ChantHelper.getMagicPowerNumInDrawPile()));
            this.addToBot(new ExhaustManaInHandAction(ChantHelper.getMagicPowerNumInHand()));
            this.addToBot(new ExhaustManaInDiscardPileAction(ChantHelper.getMagicPowerNumInDiscardPile()));
            this.addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.baseDamage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
        }
    }

    @Override
    public void onDrawOrDiscard() {
        this.updateDescription();
    }
    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        this.updateDescription();
    }

    public void updateDescription() {
        int rate = this.amount * 3;
        this.baseDamage = ChantHelper.getAllMagicPowerNum() * rate;
        this.description = String.format(descriptions[0], rate, this.baseDamage);
    }

}