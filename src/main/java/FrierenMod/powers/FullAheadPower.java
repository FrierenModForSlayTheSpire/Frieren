package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static FrierenMod.gameHelpers.ChantHelper.getManaNumInDiscardPile;
import static FrierenMod.gameHelpers.ChantHelper.getManaNumInDrawPile;

public class FullAheadPower extends AbstractFrierenPower {
    public static final String POWER_ID = ModInformation.makeID(FullAheadPower.class.getSimpleName());
    public boolean takeEffect;

    public FullAheadPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
        this.takeEffect = false;
    }

    public void afterChantFinished() {
        if(!takeEffect){
            this.flash();
            if (getManaNumInDrawPile() == getManaNumInDiscardPile()) {
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, amount), amount));
            } else
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, this.amount * 2), this.amount * 2));
            this.takeEffect = true;
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.takeEffect = false;
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount, this.amount * 2);
    }
}