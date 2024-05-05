package FrierenMod.powers.EnemySpell;

import FrierenMod.powers.SpellCasterPower;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.exordium.Cultist;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CatchBirdSpell extends AbstractEnemySpell {
    public final String SPELL_NAME = descriptions[12];
    public final String SPELL_CONTENT = descriptions[13];
    public static final int MANA_NEED = 20;

    public CatchBirdSpell(AbstractCreature owner) {
        super(owner);
    }

    @Override
    public void update() {
        int recycleTimes = 0;
        AbstractPower po = owner.getPower(SpellCasterPower.POWER_ID);
        if (po instanceof SpellCasterPower) {
            recycleTimes = ((SpellCasterPower) po).currentRecycleTimes;
        }
        Cultist mo = new Cultist(-250.0F * Settings.scale * (recycleTimes + 1), 0);
        mo.nextMove = (byte) 3;
        this.addToBot(new SpawnMonsterAction(mo, true));
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
