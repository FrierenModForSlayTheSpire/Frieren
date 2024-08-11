package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.powers.Factor001Power;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;

public class Factor001 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(Factor001.class.getSimpleName());

    public Factor001() {
        super(ID);
        loadRarity(MagicItemRarity.RARE);
        this.manaNeedMultipleCoefficient = 3;
        this.manaNeedAddCoefficient = 3;
        this.tags.add(Enum.CAN_NOT_RANDOM_GENERATED_IN_COMBAT);
    }

    @Override
    public void takeEffect() {
        if(currentSlot == 2)
            this.addToBot(new ApplyPowerAction(p, p, new Factor001Power(p)));
    }
}
