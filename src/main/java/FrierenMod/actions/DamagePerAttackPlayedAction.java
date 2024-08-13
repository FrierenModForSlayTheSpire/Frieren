package FrierenMod.actions;


import FrierenMod.cards.tempCards.Mana;
import FrierenMod.gameHelpers.CombatHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DamagePerAttackPlayedAction extends AbstractGameAction {
    private final DamageInfo info;
    private final Type type;

    public DamagePerAttackPlayedAction(Type type, AbstractCreature target, DamageInfo info, AbstractGameAction.AttackEffect effect) {
        this.type = type;
        this.info = info;
        setValues(target, info);
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.attackEffect = effect;
    }
    public void update() {
        this.isDone = true;
        if (this.target != null && this.target.currentHealth > 0) {
            int count = 0;
            if (this.type == Type.SynchroTimes)
                for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
                    if (c instanceof Mana)
                        count++;
                }
            if (this.type == Type.ChantCardUsedTimes)
                count = CombatHelper.getChantCardUsedThisTurn() * 2;
            for (int i = 0; i < count; i++)
                addToTop(new DamageAction(this.target, this.info, this.attackEffect));
        }
    }

    public enum Type {
        ChantCardUsedTimes,
        SynchroTimes
    }
}
