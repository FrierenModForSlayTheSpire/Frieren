package FrierenMod.variables;

import FrierenMod.cards.AbstractFrierenCard;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class ChantXVariable extends DynamicVariable {
    @Override
    public String key() {
        return "CX";
    }

    @Override
    public boolean isModified(AbstractCard abstractCard) {
        return ((AbstractFrierenCard)abstractCard).isChantXModified;
    }

    @Override
    public int value(AbstractCard abstractCard) {
        return ((AbstractFrierenCard)abstractCard).chantX;
    }

    @Override
    public int baseValue(AbstractCard abstractCard) {
        return ((AbstractFrierenCard)abstractCard).baseChantX;
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard) {
        return ((AbstractFrierenCard)abstractCard).upgradedChantX;
    }
}
