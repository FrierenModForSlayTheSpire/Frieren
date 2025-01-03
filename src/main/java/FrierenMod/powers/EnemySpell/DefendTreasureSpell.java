package FrierenMod.powers.EnemySpell;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.BarricadePower;

public class DefendTreasureSpell extends AbstractEnemySpell {
    public final String SPELL_NAME = descriptions[0];
    public final String SPELL_CONTENT = descriptions[1];
    public static final int MANA_NEED = 10;
    public static final int BLOCK_GAIN = 10;
    public static final int BLOCK_GAIN_ASC = 15;

    public DefendTreasureSpell(AbstractCreature owner) {
        super(owner);
    }
    @Override
    public void update() {
        addToBot(new ApplyPowerAction(owner, owner, new BarricadePower(owner)));
        if(AbstractDungeon.ascensionLevel >= 19)
            addToBot(new GainBlockAction(owner, BLOCK_GAIN_ASC));
        else
            addToBot(new GainBlockAction(owner, BLOCK_GAIN));
    }

    public String getDescription() {
        if(AbstractDungeon.ascensionLevel >= 19)
            return getSpellSignal(isDone) + SPELL_NAME + String.format(SPELL_CONTENT, MANA_NEED, BLOCK_GAIN_ASC);
        else
            return getSpellSignal(isDone) + SPELL_NAME + String.format(SPELL_CONTENT, MANA_NEED, BLOCK_GAIN);
    }

    @Override
    public int getManaNeed() {
        return MANA_NEED;
    }
}
