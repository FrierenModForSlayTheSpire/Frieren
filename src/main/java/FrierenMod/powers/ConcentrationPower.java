package FrierenMod.powers;

import FrierenMod.actions.ModifyPowerStackAmtAction;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ConcentrationPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(ConcentrationPower.class.getSimpleName());

    public ConcentrationPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractPower po = p.getPower(SuperSeriousPower.POWER_ID);
        if (po != null) {
            this.addToBot(new ModifyPowerStackAmtAction(this, 1));
            this.addToBot(new ModifyPowerStackAmtAction(po, -1));
        } else {
            this.addToBot(new ModifyPowerStackAmtAction(this, -1));
        }
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}
