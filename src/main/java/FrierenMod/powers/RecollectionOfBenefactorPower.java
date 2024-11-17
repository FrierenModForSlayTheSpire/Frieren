package FrierenMod.powers;

import FrierenMod.patches.PanelPatch;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RecollectionOfBenefactorPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(RecollectionOfBenefactorPower.class.getSimpleName());

    public RecollectionOfBenefactorPower() {
        super(POWER_ID, AbstractDungeon.player, PowerType.BUFF);
        this.updateDescription();
        PanelPatch.PanelField.FernPanel.get(AbstractDungeon.player).setRate(4);
    }

    public void updateDescription() {
        this.description = descriptions[0];
    }
}
