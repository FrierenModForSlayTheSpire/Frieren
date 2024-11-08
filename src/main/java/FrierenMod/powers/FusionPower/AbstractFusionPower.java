package FrierenMod.powers.FusionPower;

import FrierenMod.cardMods.SpecializedOffensiveMagicMod;
import FrierenMod.powers.AbstractBasePower;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public abstract class AbstractFusionPower extends AbstractBasePower {
    public AbstractCardModifier modifier;

    public AbstractFusionPower(String id, int amount) {
        super(id, AbstractDungeon.player, amount, PowerType.BUFF);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (this.modifier != null && this.modifier instanceof SpecializedOffensiveMagicMod)
            ((SpecializedOffensiveMagicMod) this.modifier).updateAmt(this.amount);
    }
}
