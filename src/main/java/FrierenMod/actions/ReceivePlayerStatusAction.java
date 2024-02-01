package FrierenMod.actions;

import FrierenMod.helpers.Status;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.screens.compendium.PotionViewScreen;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class ReceivePlayerStatusAction extends AbstractGameAction {
    private final Status status;
    private AbstractGameAction nextAction;
    public ReceivePlayerStatusAction(Status status, AbstractGameAction nextAction){
        this.status = status;
        this.nextAction = nextAction;
    }
    @Override
    public void update() {
        AbstractDungeon.player.currentHealth = status.hp;
        AbstractDungeon.player.currentBlock = status.block;
        AbstractDungeon.player.maxHealth = status.maxHp;
        AbstractDungeon.player.relics.clear();
        AbstractDungeon.player.relics.addAll(status.relics);

        AbstractDungeon.player.powers.clear();
        AbstractDungeon.player.powers.addAll(status.powers);
        for (int i = 0; i < AbstractDungeon.player.powers.size(); i++) {
            AbstractDungeon.player.powers.get(i).amount = status.powerAmt.get(i);
        }
        for(AbstractPotion potion:AbstractDungeon.player.potions){
            AbstractDungeon.player.removePotion(potion);
        }
        AbstractDungeon.player.maxOrbs = status.maxEnergy;
        EnergyPanel.totalCount = status.energy;
        AbstractDungeon.player.reorganizeRelics();
        AbstractDungeon.player.updatePowers();
        this.addToBot(nextAction);
        this.isDone = true;
    }
}
