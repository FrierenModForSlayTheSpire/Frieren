package FrierenMod.actions;

import FrierenMod.ui.panels.FernPanel;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class GainFernPanelEnergyAction extends AbstractGameAction {
    public int e;

    public GainFernPanelEnergyAction(int energy) {
        this.e = energy;
    }

    @Override
    public void update() {
        FernPanel.addEnergy(e);
        this.isDone = true;
    }
}
