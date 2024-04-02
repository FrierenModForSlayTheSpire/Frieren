package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
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
        this.flash();
        AbstractPlayer p = AbstractDungeon.player;
        AbstractPower po = p.getPower(SuperSeriousPower.POWER_ID);
        if (po != null) {
            this.amount++;
            po.amount--;
            po.flash();
            po.updateDescription();
            if (po.amount == 0)
                this.addToBot(new RemoveSpecificPowerAction(p, p, po));
        } else {
            this.amount--;
        }
        if (this.amount == 0) {
            this.addToBot(new RemoveSpecificPowerAction(p, p, this));
        }
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}
