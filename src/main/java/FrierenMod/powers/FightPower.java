package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FightPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(FightPower.class.getSimpleName());

    public FightPower(int amount) {
        super(POWER_ID, AbstractDungeon.player, amount, PowerType.BUFF);
        this.updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void beforeGainSpecializedOffensiveMagic(AbstractCard magic) {
        magic.modifyCostForCombat(-9);
    }

    @Override
    public void afterGainSpecializedOffensiveMagic(AbstractCard magic) {
        this.addToBot(new ReducePowerAction(owner, owner, this, 1));
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], amount);
    }
}
