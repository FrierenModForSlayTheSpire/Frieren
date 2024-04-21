package FrierenMod.powers.EnemySpell;

import FrierenMod.powers.SpellCasterPower;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.exordium.Cultist;

public class CatchBirdSpell extends AbstractEnemySpell {
    public final String SPELL_NAME = descriptions[12];
    public final String SPELL_CONTENT = descriptions[13];
    public static final int MANA_NEED = 20;
    private final int recycleTimes;

    public CatchBirdSpell(AbstractCreature owner) {
        super(owner);
        SpellCasterPower po = (SpellCasterPower) owner.getPower(SpellCasterPower.POWER_ID);
        if(po != null){
            this.recycleTimes = po.currentRecycleTimes;
        }else
            recycleTimes = 0;
    }

    @Override
    public void update() {
        this.addToBot(new SpawnMonsterAction(new Cultist(-250.0F * Settings.scale * (recycleTimes + 1), 0), true));
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
