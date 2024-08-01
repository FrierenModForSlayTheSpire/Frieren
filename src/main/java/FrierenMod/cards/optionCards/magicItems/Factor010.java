package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.powers.Factor010Power;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;

public class Factor010 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(Factor010.class.getSimpleName());

    public Factor010() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.UNCOMMON;
        this.manaNeedMultipleCoefficient = 2;
        this.manaNeedAddCoefficient = 2;
        this.tags.add(Enum.CAN_NOT_RANDOM_GENERATED_IN_COMBAT);
    }

    @Override
    public void takeEffect() {
        if(currentSlot == 1)
            this.addToBot(new ApplyPowerAction(p, p, new Factor010Power(p)));
    }
}
