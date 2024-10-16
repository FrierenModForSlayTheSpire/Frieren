package FrierenMod.powers.EnemySpell;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.IntangiblePower;

public class FlyingMagic extends AbstractEnemySpell {
    public final String SPELL_NAME = descriptions[14];
    public final String SPELL_CONTENT = descriptions[15];
    public static final int MANA_NEED = 50;
    public static final int POWER_GAIN = 1;

    public FlyingMagic(AbstractCreature owner) {
        super(owner);
    }

    @Override
    public void update() {
        IntangiblePower po = new IntangiblePower(owner, POWER_GAIN);
        ReflectionHacks.setPrivate(po, IntangiblePower.class, "justApplied", false);
        this.addToBot(new ApplyPowerAction(owner, owner, po));
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
