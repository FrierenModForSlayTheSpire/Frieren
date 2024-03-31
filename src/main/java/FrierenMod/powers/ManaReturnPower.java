package FrierenMod.powers;

import FrierenMod.cards.canAutoAdd.tempCards.Mana;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class ManaReturnPower extends AbstractFrierenPower {
    public static final String POWER_ID = ModInformation.makeID(ManaReturnPower.class.getSimpleName());

    public ManaReturnPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.DEBUFF);
    }
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
            this.flash();
            this.addToTop(new MakeTempCardInDiscardAction(new Mana(), this.amount));
        }
        return damageAmount;
    }
    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }

}