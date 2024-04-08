package FrierenMod.powers;

import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DancingPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(DancingPower.class.getSimpleName());

    public DancingPower(AbstractCreature owner) {
        super(POWER_ID, owner, -1, PowerType.BUFF);
        this.updateDescription();
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (CombatHelper.getConcentrationPowerAmt() == 0) {
            AbstractPlayer p = AbstractDungeon.player;
            this.addToBot(new ApplyPowerAction(p, p, new ConcentrationPower(p, CombatHelper.getCardsUsedThisTurnSize(false) + 2)));
        }
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}
