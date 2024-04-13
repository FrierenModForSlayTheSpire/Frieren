package FrierenMod.actions;

import FrierenMod.gameHelpers.CombatHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;

public class RustCleanMagicAction extends AbstractGameAction {
    private final int magicNumber;

    public RustCleanMagicAction(AbstractCreature target, AbstractCreature source, int magicNumber) {
        this.setValues(target, source, this.amount);
        this.magicNumber = magicNumber;
    }

    public void update() {
        if (!this.target.isDying && !this.target.isDead) {
            if (this.target instanceof AbstractMonster && ((AbstractMonster) this.target).type != AbstractMonster.EnemyType.BOSS) {
                for (AbstractPower po : this.target.powers)
                    if (po.type == AbstractPower.PowerType.BUFF) {
                        this.addToBot(new RemoveSpecificPowerAction(this.target, this.source, po));
                        break;
                    }
            }
        }
        if (CombatHelper.getAllManaNum() > this.magicNumber)
            this.addToBot(new ApplyPowerAction(this.source, this.source, new ArtifactPower(this.source, 1)));
        this.tickDuration();
        this.isDone = true;
    }
}
