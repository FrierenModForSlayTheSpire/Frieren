package FrierenMod.powers;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class RitesPreparationPower extends AbstractFrierenPower {
    public static final String POWER_ID = ModInformation.makeID(RitesPreparationPower.class.getSimpleName());
    private int MagicPowerPlayedNum = 0;
    public RitesPreparationPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
    }
    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        super.onAfterCardPlayed(usedCard);
        if(usedCard instanceof AbstractBaseCard && ((AbstractBaseCard) usedCard).isMana){
            this.MagicPowerPlayedNum++;
        }
        if(this.MagicPowerPlayedNum == 3){
            this.flash();
            this.addToBot(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(this.amount * 8, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
            this.MagicPowerPlayedNum = 0;
        }
        this.updateDescription();
    }
    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount * 8, this.MagicPowerPlayedNum);
    }
}
