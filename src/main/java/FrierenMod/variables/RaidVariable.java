package FrierenMod.variables;

import FrierenMod.cards.AbstractBaseCard;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class RaidVariable extends DynamicVariable {
    @Override
    public String key() {
        return "R";
    }

    @Override
    public boolean isModified(AbstractCard abstractCard) {
        return ((AbstractBaseCard)abstractCard).isRaidNumberModified;
    }

    @Override
    public int value(AbstractCard abstractCard) {
        return ((AbstractBaseCard)abstractCard).raidNumber;
    }

    @Override
    public int baseValue(AbstractCard abstractCard) {
        return ((AbstractBaseCard)abstractCard).baseRaidNumber;
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard) {
        return ((AbstractBaseCard)abstractCard).upgradedRaidNumber;
    }
}

