package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FumeshroomFormPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(FumeshroomFormPower.class.getSimpleName());

    public FumeshroomFormPower() {
        super(POWER_ID, AbstractDungeon.player, PowerType.BUFF);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = descriptions[0];
    }
}
