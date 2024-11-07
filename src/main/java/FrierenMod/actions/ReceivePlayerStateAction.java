package FrierenMod.actions;

import FrierenMod.gameHelpers.State;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class ReceivePlayerStateAction extends AbstractGameAction {
    private final State state;

    public ReceivePlayerStateAction(State state) {
        this.state = state;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        p.currentHealth = state.hp;
        p.healthBarUpdatedEvent();
        p.currentBlock = state.block;
        p.maxHealth = state.maxHp;
        p.relics.clear();
        p.relics.addAll(state.relics);
        p.powers.clear();
        p.powers.addAll(state.powers);
        for (AbstractPotion potion : p.potions) {
            p.removePotion(potion);
        }
        p.maxOrbs = state.maxEnergy;
        EnergyPanel.totalCount = state.energy;
        p.reorganizeRelics();
        p.updatePowers();
        for (AbstractPotion potion : state.potions) {
            AbstractDungeon.player.obtainPotion(potion);
        }
        AbstractDungeon.actionManager.cardsPlayedThisTurn.addAll(state.cardsPlayedThisTurn);
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
            mo.createIntent();
        }
        this.isDone = true;
    }
}
