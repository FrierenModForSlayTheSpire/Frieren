package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FernRushdownPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(FernRushdownPower.class.getSimpleName());

    public FernRushdownPower(int amount) {
        super(POWER_ID, AbstractDungeon.player, amount, PowerType.BUFF);
        this.updateDescription();
    }

    @Override
    public void afterRaidTriggered() {
        this.addToBot(new DrawCardAction(this.amount));
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], amount);
    }
}
