package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class GainRelicPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(GainRelicPower.class.getSimpleName());
    public GainRelicPower(AbstractCreature owner,int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
    }

    public void onVictory() {
        super.onVictory();
        addRandomRelicToReward(this.amount);
        flash();
    }

    public static void addRandomRelicToReward(int amount) {
        for (int i = 0; i < amount; i++) {
            AbstractRelic.RelicTier relicTier = AbstractDungeon.returnRandomRelicTier();
            AbstractRelic relic = AbstractDungeon.returnRandomRelic(relicTier);
            AbstractDungeon.getCurrRoom().addRelicToRewards(relic);
        }
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}
