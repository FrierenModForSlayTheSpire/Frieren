package FrierenMod.powers.EnemySpell;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;

public class PerfectDefensiveMagic extends AbstractEnemySpell {
    public final String SPELL_NAME = descriptions[10];
    public final String SPELL_CONTENT = descriptions[11];
    public static final int MANA_NEED = 30;
    public static final int POWER_GAIN_1 = 2;
    public static final int POWER_GAIN_2 = 5;

    public PerfectDefensiveMagic(AbstractCreature owner) {
        super(owner);
    }

    @Override
    public void update() {
        this.addToBot(new ApplyPowerAction(owner, owner, new BufferPower(owner, POWER_GAIN_1)));
        this.addToBot(new ApplyPowerAction(owner, owner, new MetallicizePower(owner, POWER_GAIN_2)));
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
