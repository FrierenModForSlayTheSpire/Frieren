package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ConcentrationPower extends AbstractBasePower{
    public static final String POWER_ID = ModInformation.makeID(ConcentrationPower.class.getSimpleName());
    public ConcentrationPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
    }
    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        this.flash();
        this.amount--;
        if(this.amount == 0){
            this.addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player,AbstractDungeon.player,this));
        }
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0],this.amount);
    }
}
