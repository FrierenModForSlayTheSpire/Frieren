package FrierenMod.variables;

import FrierenMod.cards.AbstractBaseCard;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class ChantXVariable extends DynamicVariable {
    @Override
    public String key() {
        return "CX";
    }

    @Override
    public boolean isModified(AbstractCard abstractCard) {
        return ((AbstractBaseCard)abstractCard).isChantXModified;
    }

    @Override
    public int value(AbstractCard abstractCard) {
        return ((AbstractBaseCard)abstractCard).chantX;
    }

    @Override
    public int baseValue(AbstractCard abstractCard) {
        return ((AbstractBaseCard)abstractCard).baseChantX;
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard) {
        return ((AbstractBaseCard)abstractCard).upgradedChantX;
    }
}
