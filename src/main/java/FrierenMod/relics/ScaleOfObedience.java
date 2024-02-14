package FrierenMod.relics;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ScaleOfObedience extends AbstractFrierenRelic {
    public static final String ID = ModInformation.makeID(ScaleOfObedience.class.getSimpleName());
    public ScaleOfObedience() {
        super(ID, RelicTier.BOSS);
    }
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new ScaleOfObedience();
    }
    public boolean isNormal(){
        for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
            if (m.type == AbstractMonster.EnemyType.NORMAL)
                return true;
        }
        return false;
    }
    public void atTurnStart() {
        if (isNormal()) {
            flash();
            addToTop(new GainEnergyAction(1));
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            addToBot(new DrawCardAction(AbstractDungeon.player, 1));
        }
    }
}