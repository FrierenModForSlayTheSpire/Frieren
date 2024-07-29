package FrierenMod.actions;

import FrierenMod.gameHelpers.CombatHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SimmeringPowerAction extends AbstractGameAction {
    private final int amt;

    public SimmeringPowerAction(int amt) {
        this.amt = amt;
        this.actionType = ActionType.POWER;
    }

    @Override
    public void update() {
        System.out.println(AbstractDungeon.actionManager.actions);
        int draw = CombatHelper.getManaNumInDrawPile();
        int hand = CombatHelper.getManaNumInHand();
        int discard = CombatHelper.getManaNumInDiscardPile();
        if (draw == 0 && discard == 0 && hand == 0) {
            this.isDone = true;
            return;
        }
        if (draw > 0) {
            this.addToBot(new ExhaustManaInCardGroupAction(1,0));
        } else if (discard > 0) {
            this.addToBot(new ExhaustManaInCardGroupAction(1,2));
        } else if (hand > 0) {
            this.addToBot(new ExhaustManaInCardGroupAction(1,1));
        }
        this.addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.amt, true), DamageInfo.DamageType.THORNS, AttackEffect.SLASH_HORIZONTAL, true));
        this.isDone = true;
    }
}
