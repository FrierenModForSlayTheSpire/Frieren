package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;

public class Factor001Power extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(Factor001Power.class.getSimpleName());

    public Factor001Power(AbstractCreature owner) {
        super(POWER_ID, owner, AbstractPower.PowerType.BUFF);
        this.updateDescription();
    }

    @Override
    public void onInitialApplication() {
        if (!owner.hasPower(Factor010Power.POWER_ID) || !owner.hasPower(Factor100Power.POWER_ID)) {
            this.addToBot(new RemoveSpecificPowerAction(owner, owner, Factor001Power.POWER_ID));
            this.addToBot(new RemoveSpecificPowerAction(owner, owner, Factor100Power.POWER_ID));
            this.addToBot(new RemoveSpecificPowerAction(owner, owner, Factor010Power.POWER_ID));
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        this.addToBot(new RemoveSpecificPowerAction(owner, owner, Factor001Power.POWER_ID));
        this.addToBot(new RemoveSpecificPowerAction(owner, owner, Factor100Power.POWER_ID));
        this.addToBot(new RemoveSpecificPowerAction(owner, owner, Factor010Power.POWER_ID));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (owner.hasPower(Factor001Power.POWER_ID) && owner.hasPower(Factor010Power.POWER_ID) && owner.hasPower(Factor001Power.POWER_ID)) {
            this.addToBot(new VFXAction(new GrandFinalEffect(), 0.7F));
            for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters)
                if (!mo.halfDead && !mo.isDying && !mo.isEscaping)
                    this.addToBot(new InstantKillAction(mo));
        }
        this.addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    public void updateDescription() {
        this.description = descriptions[0];
    }
}
