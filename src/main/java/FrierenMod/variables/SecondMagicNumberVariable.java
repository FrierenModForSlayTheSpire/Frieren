package FrierenMod.variables;

import FrierenMod.cards.AbstractMagicianCard;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class SecondMagicNumberVariable extends DynamicVariable {
    @Override
    public String key() {
        return "SM";
    }

    @Override
    public boolean isModified(AbstractCard abstractCard) {
        return ((AbstractMagicianCard)abstractCard).isSecondMagicNumberModified;
    }

    @Override
    public int value(AbstractCard abstractCard) {
        return ((AbstractMagicianCard)abstractCard).secondMagicNumber;
    }

    @Override
    public int baseValue(AbstractCard abstractCard) {
        return ((AbstractMagicianCard)abstractCard).baseSecondMagicNumber;
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard) {
        return ((AbstractMagicianCard)abstractCard).upgradedSecondMagicNumber;
    }
}
