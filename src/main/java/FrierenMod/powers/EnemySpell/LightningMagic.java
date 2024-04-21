package FrierenMod.powers.EnemySpell;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class LightningMagic extends AbstractEnemySpell {
    public final String SPELL_NAME = descriptions[8];
    public final String SPELL_CONTENT = descriptions[9];
    public static final int MANA_NEED = 20;
    public static final int POWER_GAIN_1 = 2;
    public static final int POWER_GAIN_2 = 2;

    public LightningMagic(AbstractCreature owner, AbstractCreature target) {
        super(owner, target);
    }

    @Override
    public void update() {
        this.addToBot(new ApplyPowerAction(target, owner, new VulnerablePower(target, POWER_GAIN_1, true)));
        this.addToBot(new ApplyPowerAction(target, owner, new WeakPower(target, POWER_GAIN_2, true)));
    }

    @Override
    public String getDescription() {
        return getSpellSignal(isDone) + SPELL_NAME + String.format(SPELL_CONTENT, MANA_NEED, POWER_GAIN_1, POWER_GAIN_2);
    }

    @Override
    public int getManaNeed() {
        return MANA_NEED;
    }
}
