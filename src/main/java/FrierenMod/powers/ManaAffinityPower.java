package FrierenMod.powers;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ManaAffinityPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(ManaAffinityPower.class.getSimpleName());

    public ManaAffinityPower() {
        super(POWER_ID, AbstractDungeon.player, PowerType.BUFF);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = descriptions[0];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(AbstractBaseCard.Enum.MANA)) {
                c.selfRetain = true;
            }
        }
    }
}
