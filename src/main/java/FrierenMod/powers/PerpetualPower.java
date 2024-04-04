package FrierenMod.powers;

import FrierenMod.actions.MakeManaInDiscardAction;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PerpetualPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(PerpetualPower.class.getSimpleName());

    public PerpetualPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
        this.updateDescription();
    }

    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            this.addToBot(new MakeManaInDiscardAction(this.amount * 2,true));
        }
    }
    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount * 2);
    }

}