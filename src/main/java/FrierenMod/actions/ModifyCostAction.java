package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;

import java.util.UUID;

public class ModifyCostAction extends AbstractGameAction {
    UUID uuid;
    public ModifyCostAction(UUID targetUUID, int amount) {
        this.setValues(this.target, this.source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.uuid = targetUUID;
    }

    public void update() {
        for(AbstractCard c:GetAllInBattleInstances.get(this.uuid)){
            c.cost += this.amount;
            if(c.cost < 0){
                c.cost = 0;
            }
            c.costForTurn = c.cost;
            c.isCostModified = false;
        }
        this.isDone = true;
    }
}
