package FrierenMod.actions;

import FrierenMod.cards.AbstractBaseCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;

import java.util.UUID;

public class ModifyMagicNumberAction extends AbstractGameAction {
    UUID uuid;

    public ModifyMagicNumberAction(UUID targetUUID, int amount) {
        this.setValues(this.target, this.source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.uuid = targetUUID;
    }

    public void update() {
        for (AbstractCard c : GetAllInBattleInstances.get(uuid)) {
            if (c instanceof AbstractBaseCard) {
                ((AbstractBaseCard) c).baseRaidNumber += this.amount;
                if (((AbstractBaseCard) c).baseRaidNumber < 0)
                    ((AbstractBaseCard) c).baseRaidNumber = 0;
            }
        }
        this.isDone = true;
    }
}
