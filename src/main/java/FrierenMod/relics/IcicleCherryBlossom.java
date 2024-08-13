package FrierenMod.relics;

import FrierenMod.utils.ModInformation;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

@AutoAdd.Ignore
public class IcicleCherryBlossom extends AbstractBaseRelic {
    public static final String ID = ModInformation.makeID(IcicleCherryBlossom.class.getSimpleName());
    public static final int RATE = 60;

    public IcicleCherryBlossom() {
        super(ID, RelicTier.BOSS);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new IcicleCherryBlossom();
    }

    public boolean isNormal() {
        for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
            if (m.type == AbstractMonster.EnemyType.BOSS)
                return true;
        }
        return false;
    }

    public void atBattleStart() {
        flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    public void onEquip() {
        AbstractDungeon.player.energy.energyMaster++;
    }

    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster--;
    }

}