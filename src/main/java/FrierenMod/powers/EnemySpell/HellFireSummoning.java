package FrierenMod.powers.EnemySpell;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class HellFireSummoning extends AbstractEnemySpell {
    public final String SPELL_NAME = descriptions[6];
    public final String SPELL_CONTENT = descriptions[7];
    public static final int MANA_NEED = 30;
    public static final int CARD_MAKE = 2;
    public static final int CARD_MAKE_ASC = 3;

    public HellFireSummoning(AbstractCreature owner) {
        super(owner);
    }

    @Override
    public void update() {
        if (AbstractDungeon.ascensionLevel >= 19)
            this.addToBot(new MakeTempCardInDrawPileAction(new Burn(), CARD_MAKE_ASC, true, true));
        else
            this.addToBot(new MakeTempCardInDrawPileAction(new Burn(), CARD_MAKE, true, true));
    }

    @Override
    public String getDescription() {
        if(AbstractDungeon.ascensionLevel >= 19)
            return getSpellSignal(isDone) + SPELL_NAME + String.format(SPELL_CONTENT, MANA_NEED, CARD_MAKE_ASC);
        else
            return getSpellSignal(isDone) + SPELL_NAME + String.format(SPELL_CONTENT, MANA_NEED, CARD_MAKE);
    }

    @Override
    public int getManaNeed() {
        return MANA_NEED;
    }
}
