package FrierenMod.relics;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ImmovableCloak extends AbstractBaseRelic {
    public static final String ID = ModInformation.makeID(ImmovableCloak.class.getSimpleName());
    public ImmovableCloak() {
        super(ID, RelicTier.UNCOMMON);
    }
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
    private int count=0;
    public void atBattleStart() {
        this.count=0;
        beginLongPulse();
    }

    public void atTurnStart() {
        if(count==0) {
            flash();
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            addToBot(new DrawCardAction(AbstractDungeon.player, 1));
        }
        }


public int onLoseHpLast(int damageAmount) {
        if (damageAmount > 0 && count == 0) {
            flash();
            stopPulse();
            this.grayscale = true;
            count=1;
        }
    return damageAmount;
    }
public AbstractRelic makeCopy() {
    return new ImmovableCloak();
}
}