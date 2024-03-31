package FrierenMod.relics;

import FrierenMod.cards.canAutoAdd.tempCards.Laziness;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ComfortableBed extends AbstractBaseRelic {
    public static final String ID = ModInformation.makeID(ComfortableBed.class.getSimpleName());

    public ComfortableBed() {
        super(ID, RelicTier.BOSS);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new ComfortableBed();
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
        this.addToBot(new MakeTempCardInDiscardAndDeckAction(new Laziness().makeCopy()));
    }

    public void onEquip() {
        AbstractDungeon.player.energy.energyMaster++;
    }

    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster--;
    }

}