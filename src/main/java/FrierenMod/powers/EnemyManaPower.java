package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class EnemyManaPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(EnemyManaPower.class.getSimpleName());

    public EnemyManaPower(AbstractCreature owner, int amt) {
        super(POWER_ID, owner, amt, PowerType.BUFF);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = descriptions[0];
    }
}
