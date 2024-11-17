package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MysticEyePower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(MysticEyePower.class.getSimpleName());

    public MysticEyePower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.DEBUFF);
        this.updateDescription();
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
            this.flash();
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, owner, new ConcentrationPower(amount)));
        }
        return damageAmount;
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }

}