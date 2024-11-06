package FrierenMod.powers;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ConcentrationPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(ConcentrationPower.class.getSimpleName());

    public ConcentrationPower(int amount) {
        super(POWER_ID, AbstractDungeon.player, amount, PowerType.BUFF);
        this.updateDescription();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.hasTag(AbstractBaseCard.Enum.MANA)) {
            return;
        }
        if (card.hasTag(AbstractBaseCard.Enum.SPEED)) {
            return;
        }
        this.addToBot(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, this, 1));
    }

    public void updateDescription() {
        this.description = descriptions[0];
    }
}
