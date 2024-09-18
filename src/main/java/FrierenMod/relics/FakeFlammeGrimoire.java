package FrierenMod.relics;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class FakeFlammeGrimoire extends AbstractBaseRelic {
    public static final String ID = ModInformation.makeID(FakeFlammeGrimoire.class.getSimpleName());
    public boolean takeEffect;

    public FakeFlammeGrimoire() {
        super(ID, RelicTier.COMMON);
        this.takeEffect = false;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        FakeFlammeGrimoire relic = new FakeFlammeGrimoire();
        relic.takeEffect = this.takeEffect;
        return relic;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void atTurnStart() {
        this.counter++;
        if (this.counter <= 1) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.takeEffect = true;
        }else
            this.takeEffect = false;
    }

    public void onVictory() {
        this.counter = -1;
    }
}
