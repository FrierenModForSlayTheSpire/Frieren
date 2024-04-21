package FrierenMod.powers;

import FrierenMod.powers.EnemySpell.*;
import FrierenMod.utils.ModInformation;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class SpellCasterPower extends AbstractBasePower implements OnReceivePowerPower {
    public static final String POWER_ID = ModInformation.makeID(SpellCasterPower.class.getSimpleName());
    public ArrayList<AbstractEnemySpell> spellList = new ArrayList<>();
    private int currentRecycleTimes = 0;
    private static final int MAX_RECYCLE_TIMES = 2;

    public SpellCasterPower(AbstractCreature owner) {
        super(POWER_ID, owner, PowerType.BUFF);
        this.spellList.add(new DefendTreasureSpell(this.owner));
        this.spellList.add(new RapidChant(this.owner));
        this.spellList.add(new RockGolemSpell(this.owner));
        this.spellList.add(new HellFireSummoning(this.owner));
        this.spellList.add(new LightningMagic(this.owner, AbstractDungeon.player));
        this.spellList.add(new PerfectDefensiveMagic(this.owner));
        this.spellList.add(new CatchBirdSpell(this.owner));
        this.spellList.add(new FlyingMagic(this.owner));
        this.updateDescription();
    }
    public int getCurrentSpellManaNeed() {
        if(currentRecycleTimes >= MAX_RECYCLE_TIMES){
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
            return Integer.MAX_VALUE;
        }
        for (AbstractEnemySpell spell : spellList) {
            if (!spell.isDone) {
                return spell.getManaNeed();
            }
        }
        this.refreshSpellList();
        return getCurrentSpellManaNeed();
    }

    public void takeEffectCurrentSpell() {
        for (AbstractEnemySpell spell : spellList) {
            if (!spell.isDone) {
                spell.takeEffect();
                break;
            }
        }
        this.updateDescription();
    }

    public void refreshSpellList() {
        currentRecycleTimes++;
        if (currentRecycleTimes >= MAX_RECYCLE_TIMES) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
            return;
        }
        for (AbstractEnemySpell spell : spellList) {
            spell.isDone = false;
        }
        this.updateDescription();
    }

    public void updateDescription() {
        String description = "";
        for (AbstractEnemySpell spell : this.spellList) {
            description += spell.getDescription();
        }
        this.description = description;
    }

    @Override
    public boolean onReceivePower(AbstractPower po, AbstractCreature owner, AbstractCreature abstractCreature1) {
        if (po instanceof EnemyManaPower && owner == this.owner) {
            AbstractPower power = this.owner.getPower(EnemyManaPower.POWER_ID);
            int manaAmt = (power == null ? 0 : power.amount) + po.amount;
            while (manaAmt >= getCurrentSpellManaNeed()) {
                int manaNeed = getCurrentSpellManaNeed();
                if(power != null){
                    power.amount -= manaNeed;
                    power.flash();
                }
                this.takeEffectCurrentSpell();
                manaAmt -= manaNeed;
            }
            this.flash();
            this.updateDescription();
        }
        return true;
    }
}