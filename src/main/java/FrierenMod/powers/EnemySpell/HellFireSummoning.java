package FrierenMod.powers.EnemySpell;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.ThornsPower;

public class HellFireSummoning extends AbstractEnemySpell {
    public final String SPELL_NAME = descriptions[6];
    public final String SPELL_CONTENT = descriptions[7];
    public static final int MANA_NEED = 10;
    public static final int CARD_MAKE_AMT = 2;

    public HellFireSummoning(AbstractCreature owner) {
        super(owner);
    }

    @Override
    public void update() {
        this.addToBot(new MakeTempCardInDrawPileAction(new Burn(), CARD_MAKE_AMT, true, true));
        this.addToBot(new ApplyPowerAction(owner, owner, new ThornsPower(owner, CARD_MAKE_AMT)));
    }

    @Override
    public String getDescription() {
        return getSpellSignal(isDone) + SPELL_NAME + String.format(SPELL_CONTENT, MANA_NEED, CARD_MAKE_AMT);
    }

    @Override
    public int getManaNeed() {
        return MANA_NEED;
    }
}
