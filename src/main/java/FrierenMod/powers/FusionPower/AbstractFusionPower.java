package FrierenMod.powers.FusionPower;

import FrierenMod.powers.AbstractBasePower;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public abstract class AbstractFusionPower extends AbstractBasePower {
    public AbstractCardModifier modifier;
    public AbstractFusionPower(String id, int amount) {
        super(id, AbstractDungeon.player, amount, PowerType.BUFF);
    }
}
