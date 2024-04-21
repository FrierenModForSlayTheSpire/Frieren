package FrierenMod.powers.EnemySpell;

import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.exordium.Cultist;

public class CatchBirdSpell extends AbstractEnemySpell {
    public final String SPELL_NAME = descriptions[12];
    public final String SPELL_CONTENT = descriptions[13];
    public static final int MANA_NEED = 20;
    private static int postfix = 1;

    public CatchBirdSpell(AbstractCreature owner) {
        super(owner);
    }

    @Override
    public void update() {
        this.addToBot(new SpawnMonsterAction(new Cultist(-350.0F * postfix++, 0), true));
    }

    @Override
    public String getDescription() {
        return getSpellSignal(isDone) + SPELL_NAME + String.format(SPELL_CONTENT, MANA_NEED);
    }

    @Override
    public int getManaNeed() {
        return MANA_NEED;
    }
}
