package FrierenMod.actions;

import FrierenMod.helpers.Status;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class ReceivePlayerStatusAction extends AbstractGameAction {
    private final Status status;
    public ReceivePlayerStatusAction(Status status){
        this.status = status;
    }
    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        p.currentHealth = status.hp;
        p.currentBlock = status.block;
        p.maxHealth = status.maxHp;
        p.relics.clear();
        p.relics.addAll(status.relics);
        p.powers.clear();
        p.powers.addAll(status.powers);
        for (int i = 0; i < p.powers.size(); i++) {
            p.powers.get(i).amount = status.powerAmt.get(i);
        }
        for(AbstractPotion potion:p.potions){
            p.removePotion(potion);
        }
        p.maxOrbs = status.maxEnergy;
        EnergyPanel.totalCount = status.energy;
        p.reorganizeRelics();
        p.updatePowers();
        for(AbstractPotion potion: status.potions){
            this.addToBot(new ObtainPotionAction(potion));
        }
        this.isDone = true;
    }
}
