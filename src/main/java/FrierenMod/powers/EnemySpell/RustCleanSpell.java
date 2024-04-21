package FrierenMod.powers.EnemySpell;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.ArtifactPower;

public class RustCleanSpell extends AbstractEnemySpell {
    public final String SPELL_NAME = descriptions[4];
    public final String SPELL_CONTENT = descriptions[5];
    public static final int MANA_NEED = 10;
    public static final int POWER_GAIN = 1;

    public RustCleanSpell(AbstractCreature owner) {
        super(owner);
    }

    @Override
    public void update() {
        this.addToBot(new ApplyPowerAction(owner, owner, new ArtifactPower(owner, POWER_GAIN)));
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
