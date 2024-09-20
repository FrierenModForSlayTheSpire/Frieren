package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import basemod.BaseMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SwallowedPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(SwallowedPower.class.getSimpleName());

    public SwallowedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.DEBUFF);
        this.updateDescription();
    }

    @Override
    public void onInitialApplication() {
        BaseMod.MAX_HAND_SIZE -= 2;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        BaseMod.MAX_HAND_SIZE -= stackAmount;
    }

    @Override
    public void onVictory() {
        BaseMod.MAX_HAND_SIZE = 10;
    }

    @Override
    public void onRemove() {
        if(AbstractDungeon.player.hasPower(FrierenSuitcasePower.POWER_ID))
            BaseMod.MAX_HAND_SIZE = 13;
        else
            BaseMod.MAX_HAND_SIZE = 10;
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }

}