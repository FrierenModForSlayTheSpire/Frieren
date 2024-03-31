package FrierenMod.actions;

import FrierenMod.cards.AbstractMagicianCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import java.util.UUID;

public class IncreaseEleganceSecondMiscAction extends AbstractGameAction {
    private final int miscIncrease;

    private final UUID uuid;

    public IncreaseEleganceSecondMiscAction(UUID targetUUID, int miscValue, int miscIncrease) {
        this.miscIncrease = miscIncrease;
        this.uuid = targetUUID;
    }

    public void update() {
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if(c instanceof AbstractMagicianCard){
                if (!c.uuid.equals(this.uuid))
                    continue;
                ((AbstractMagicianCard) c).secondMisc += this.miscIncrease;
                c.baseMagicNumber = ((AbstractMagicianCard) c).secondMisc;
                c.isMagicNumberModified = false;
                c.applyPowers();
            }
        }
        for (AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
            if(c instanceof AbstractMagicianCard){
                ((AbstractMagicianCard) c).secondMisc += this.miscIncrease;
                c.baseMagicNumber = ((AbstractMagicianCard) c).secondMisc;
                c.applyPowers();
            }
        }
        this.isDone = true;
    }
}
