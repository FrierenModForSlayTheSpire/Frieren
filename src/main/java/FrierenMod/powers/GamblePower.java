package FrierenMod.powers;

import FrierenMod.gameHelpers.ActionHelper;
import FrierenMod.patches.fields.RandomField;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class GamblePower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(GamblePower.class.getSimpleName());

    public GamblePower(int amount) {
        super(POWER_ID, AbstractDungeon.player, amount, PowerType.BUFF);
        this.updateDescription();
        this.priority = 4;
    }

    @Override
    public void onAfterCardPlayed(AbstractCard card) {
        ActionHelper.addToBotAbstract(() -> {
            int roll = RandomField.getMagicItemRng().random(1, 6);
            if (AbstractDungeon.player.hasPower(ConcentrationPower.POWER_ID)) {
                AbstractDungeon.player.getPower(ConcentrationPower.POWER_ID).amount = roll;
                AbstractDungeon.player.getPower(ConcentrationPower.POWER_ID).flash();
            } else {
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ConcentrationPower(roll)));
            }
            if (roll == 6) {
                this.flash();
                this.addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(10, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
            }
        });
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], amount);
    }
}
