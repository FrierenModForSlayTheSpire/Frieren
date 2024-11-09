package FrierenMod.actions;

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
            c.baseMagicNumber += this.amount;
            if (c.baseMagicNumber < 0) {
                c.baseMagicNumber = 0;
            }
            c.magicNumber = c.baseMagicNumber;
        }
        this.isDone = true;
    }
}
