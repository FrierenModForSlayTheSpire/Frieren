package FrierenMod.relics;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class Stille extends AbstractBaseRelic {
    public static final String ID = ModInformation.makeID(Stille.class.getSimpleName());

    public Stille() {
        super(ID, RelicTier.COMMON);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Stille();
    }

    public void atBattleStart() {
        this.counter = 0;
        flash();
        addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new DexterityPower((AbstractCreature)AbstractDungeon.player, 2), 2));
        addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
    }


    public void atTurnStart() {
        this.counter++;
        if (this.counter==3) {
            flash();
            addToTop((AbstractGameAction) new RelicAboveCreatureAction((AbstractCreature) AbstractDungeon.player, this));
            addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new LoseDexterityPower((AbstractCreature)AbstractDungeon.player, 2), 2));
        }
    }
    public void onVictory() {
        this.counter = -1;
    }
}