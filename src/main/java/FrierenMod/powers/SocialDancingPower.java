package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SocialDancingPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(SocialDancingPower.class.getSimpleName());

    public SocialDancingPower() {
        super(POWER_ID, AbstractDungeon.player, PowerType.BUFF);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = descriptions[0];
    }
}
