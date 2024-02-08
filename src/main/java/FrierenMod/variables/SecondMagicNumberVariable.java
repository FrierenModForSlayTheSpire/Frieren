package FrierenMod.variables;

import FrierenMod.cards.AbstractFrierenCard;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class SecondMagicNumberVariable extends DynamicVariable {
    @Override
    public String key() {
        return "SM";
    }

    @Override
    public boolean isModified(AbstractCard abstractCard) {
        return ((AbstractFrierenCard)abstractCard).isSecondMagicNumberModified;
    }

    @Override
    public int value(AbstractCard abstractCard) {
        return ((AbstractFrierenCard)abstractCard).secondMagicNumber;
    }

    @Override
    public int baseValue(AbstractCard abstractCard) {
        return ((AbstractFrierenCard)abstractCard).baseSecondMagicNumber;
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard) {
        return ((AbstractFrierenCard)abstractCard).upgradedSecondMagicNumber;
    }
}
