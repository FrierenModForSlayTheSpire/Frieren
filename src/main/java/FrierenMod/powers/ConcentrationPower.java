package FrierenMod.powers;

import FrierenMod.actions.ModifyPowerStackAmtAction;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ConcentrationPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(ConcentrationPower.class.getSimpleName());
    public int changeTimes;

    public ConcentrationPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
        this.changeTimes = 0;
        this.updateDescription();
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractPower superSerious = p.getPower(SuperSeriousPower.POWER_ID);
        AbstractPower dancing = p.getPower(DancingPower.POWER_ID);
        if (dancing != null) {
            int amt = CombatHelper.getCardsUsedThisTurnSize(false) + 2;
            this.addToBot(new ModifyPowerStackAmtAction(this, amt, false, true));
            return;
        }
        if (superSerious != null) {
            this.addToBot(new ModifyPowerStackAmtAction(this, 1, false));
            this.addToBot(new ModifyPowerStackAmtAction(superSerious, -1, true));
        } else {
            this.addToBot(new ModifyPowerStackAmtAction(this, -1, false));
        }
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}
