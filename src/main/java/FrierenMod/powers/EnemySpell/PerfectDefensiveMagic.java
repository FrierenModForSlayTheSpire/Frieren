package FrierenMod.powers.EnemySpell;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.MalleablePower;
import com.megacrit.cardcrawl.powers.MetallicizePower;

public class PerfectDefensiveMagic extends AbstractEnemySpell {
    public final String SPELL_NAME = descriptions[10];
    public final String SPELL_CONTENT_ASC = descriptions[11];
    public final String SPELL_CONTENT = descriptions[16];
    public static final int MANA_NEED = 30;
    public static final int BUFFER_GAIN = 1;
    public static final int BUFFER_GAIN_ASC = 2;
    public static final int MALLEABLE_GAIN_ASC = 2;
    public static final int METALLICIZE_GAIN = 8;

    public PerfectDefensiveMagic(AbstractCreature owner) {
        super(owner);
    }

    @Override
    public void update() {
        if(AbstractDungeon.ascensionLevel >= 19){
            this.addToBot(new ApplyPowerAction(owner, owner, new BufferPower(owner, BUFFER_GAIN_ASC)));
            this.addToBot(new ApplyPowerAction(owner, owner, new MalleablePower(owner, MALLEABLE_GAIN_ASC)));
        }
        else{
            this.addToBot(new ApplyPowerAction(owner, owner, new BufferPower(owner, BUFFER_GAIN)));
            this.addToBot(new ApplyPowerAction(owner, owner, new MetallicizePower(owner, METALLICIZE_GAIN)));
        }
    }

    @Override
    public String getDescription() {
        if(AbstractDungeon.ascensionLevel >= 19)
            return getSpellSignal(isDone) + SPELL_NAME + String.format(SPELL_CONTENT_ASC, MANA_NEED, BUFFER_GAIN_ASC, MALLEABLE_GAIN_ASC);
        else
            return getSpellSignal(isDone) + SPELL_NAME + String.format(SPELL_CONTENT, MANA_NEED, BUFFER_GAIN, METALLICIZE_GAIN);
    }

    @Override
    public int getManaNeed() {
        return MANA_NEED;
    }
}
