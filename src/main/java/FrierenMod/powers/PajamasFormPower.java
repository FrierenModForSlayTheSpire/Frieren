package FrierenMod.powers;


import FrierenMod.actions.ExhaustManaInCardGroupAction;
import FrierenMod.cards.white.PajamasForm;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.gameHelpers.SlotBgHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class PajamasFormPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(PajamasFormPower.class.getSimpleName());
    private int baseDamage;

    public PajamasFormPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
        this.updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            this.updateDescription();
            this.flash();
            if(CombatHelper.getAllManaNum() >= 30)
                SlotBgHelper.unlockANewSlot("4009");
            this.addToBot(new ExhaustManaInCardGroupAction(CombatHelper.getManaNumInDrawPile(),0));
            this.addToBot(new ExhaustManaInCardGroupAction(CombatHelper.getManaNumInHand(),1));
            this.addToBot(new ExhaustManaInCardGroupAction(CombatHelper.getManaNumInDiscardPile(),2));
            this.addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.baseDamage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
        }
    }

    @Override
    public void onDrawOrDiscard() {
        this.updateDescription();
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        this.updateDescription();
    }

    public void updateDescription() {
        int perDamage = this.amount * PajamasForm.RATE;
        this.baseDamage = CombatHelper.getAllManaNum() * perDamage;
        this.description = String.format(descriptions[0], perDamage, this.baseDamage);
    }

}