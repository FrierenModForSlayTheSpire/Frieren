package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

public class DarkPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(DarkPower.class.getSimpleName());

    public DarkPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
        this.updateDescription();
    }

    @Override
    public void onInitialApplication() {
        AbstractDungeon.effectList.add(new ThoughtBubble(owner.dialogX, owner.dialogY, 3.0F, this.descriptions[1], true));
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        if(this.amount < 4)
            this.amount++;
    }

    public void updateDescription() {
        this.description = descriptions[0];
    }

}