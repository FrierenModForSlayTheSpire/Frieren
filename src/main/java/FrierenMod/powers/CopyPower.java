package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class CopyPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(CopyPower.class.getSimpleName());
    public int damage;
    public int block;

    public CopyPower(AbstractCreature owner) {
        super(POWER_ID, owner, -1, PowerType.BUFF);
        this.updateDescription();
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
            flash();
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, damageAmount)));
            this.updateDescription();
        }
        return damageAmount;
    }

    public void updateDescription() {
        this.description = descriptions[0];
    }
}
