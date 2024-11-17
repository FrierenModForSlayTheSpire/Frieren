package FrierenMod.powers;

import FrierenMod.gameHelpers.CardPoolHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MasterAndApprenticePower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(MasterAndApprenticePower.class.getSimpleName());

    public MasterAndApprenticePower(int amount) {
        super(POWER_ID, AbstractDungeon.player, amount, PowerType.BUFF);
        this.updateDescription();
    }

    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            for (int i = 0; i < this.amount; i++)
                addToBot(new MakeTempCardInHandAction(CardPoolHelper.getRandomCard(CardPoolHelper.PoolType.DUAL_CARD)));
        }
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], amount);
    }
}
