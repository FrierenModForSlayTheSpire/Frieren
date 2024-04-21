package FrierenMod.powers.EnemySpell;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.RitualPower;

public class RapidChant extends AbstractEnemySpell {
    public final String SPELL_NAME = descriptions[2];
    public final String SPELL_CONTENT = descriptions[3];
    public static final int MANA_NEED = 20;
    public static final int POWER_GAIN = 5;

    public RapidChant(AbstractCreature owner) {
        super(owner);
    }

    @Override
    public void update() {
        this.addToBot(new ApplyPowerAction(owner, owner, new RitualPower(owner, POWER_GAIN, false)));
    }

    @Override
    public String getDescription() {
        return getSpellSignal(isDone) + SPELL_NAME + String.format(SPELL_CONTENT, MANA_NEED, POWER_GAIN);
    }

    @Override
    public int getManaNeed() {
        return MANA_NEED;
    }
}
