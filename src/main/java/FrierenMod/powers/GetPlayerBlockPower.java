package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class GetPlayerBlockPower extends AbstractBasePower implements InvisiblePower {
    public static final String POWER_ID = ModInformation.makeID(GetPlayerBlockPower.class.getSimpleName());
    private final AbstractCreature receiver;

    public GetPlayerBlockPower(AbstractCreature owner, AbstractCreature receiver) {
        super(POWER_ID, owner, -1, PowerType.BUFF);
        this.receiver = receiver;
    }

    @Override
    public void onGainedBlock(float blockAmount) {
        this.addToBot(new GainBlockAction(this.receiver, (int) blockAmount));
    }
}
