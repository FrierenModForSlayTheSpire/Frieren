package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RecollectionOfBenefactorPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(RecollectionOfBenefactorPower.class.getSimpleName());

    public RecollectionOfBenefactorPower() {
        super(POWER_ID, AbstractDungeon.player, PowerType.BUFF);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = descriptions[0];
    }
}
