package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.actions.MakeManaInDiscardAction;
import FrierenMod.actions.MakeManaInDrawPileAction;
import FrierenMod.actions.MakeManaInHandAction;
import FrierenMod.utils.ModInformation;

public class BetaFactor13 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor13.class.getSimpleName());

    public BetaFactor13() {
        super(ID);
        loadRarity(MagicItemRarity.UNCOMMON);
    }

    @Override
    public void takeEffect() {
        if(currentSlot == 0)
            addToBot(new MakeManaInHandAction(secondMagicNumber));
        else if(currentSlot == 1)
            addToBot(new MakeManaInDiscardAction(secondMagicNumber));
        else if (currentSlot == 2)
            addToBot(new MakeManaInDrawPileAction(secondMagicNumber));
    }
}
