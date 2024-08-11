package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.powers.Factor100Power;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;

public class Factor100 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(Factor100.class.getSimpleName());

    public Factor100() {
        super(ID);
        loadRarity(MagicItemRarity.COMMON);
        this.manaNeedMultipleCoefficient = 1;
        this.manaNeedAddCoefficient = 1;
        this.tags.add(Enum.CAN_NOT_RANDOM_GENERATED_IN_COMBAT);
    }

    @Override
    public void takeEffect() {
        if(currentSlot == 0)
            this.addToBot(new ApplyPowerAction(p, p, new Factor100Power(p)));
    }
}
