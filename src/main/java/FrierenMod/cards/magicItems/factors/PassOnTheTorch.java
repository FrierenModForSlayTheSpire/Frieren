package FrierenMod.cards.magicItems.factors;

import FrierenMod.actions.MakeManaInDiscardAction;
import FrierenMod.actions.MakeManaInDrawPileAction;
import FrierenMod.actions.MakeManaInHandAction;
import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.utils.ModInformation;

public class PassOnTheTorch extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(PassOnTheTorch.class.getSimpleName());

    public PassOnTheTorch() {
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
