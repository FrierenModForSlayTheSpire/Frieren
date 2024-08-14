package FrierenMod.powers;

import FrierenMod.actions.ReceiveEverythingAction;
import FrierenMod.gameHelpers.Status;
import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.TimeWarpTurnEndEffect;

public class TimeTravelPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(TimeTravelPower.class.getSimpleName());
    private final Status status;
    private final boolean upgraded;
    private int counts = 2;

    public TimeTravelPower(AbstractCreature owner, Status status, boolean upgraded) {
        super(POWER_ID, owner, PowerType.BUFF);
        this.status = status;
        this.upgraded = upgraded;
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.counts);
    }

    @Override
    public void atStartOfTurn() {
        counts--;
        this.updateDescription();
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            if (counts == 0) {
                CardCrawlGame.sound.play("POWER_TIME_WARP", 0.05F);
                AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.GOLD, true));
                AbstractDungeon.topLevelEffectsQueue.add(new TimeWarpTurnEndEffect());
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
                this.addToBot(new ReceiveEverythingAction(this.status, upgraded));
            }
        }
    }
}