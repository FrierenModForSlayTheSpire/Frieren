package FrierenMod.actions;

import FrierenMod.cards.AbstractFrierenCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import java.util.UUID;
@Deprecated
public class EleganceAction extends AbstractGameAction {
    private final DamageInfo info;

    private final AbstractFrierenCard c;
    public EleganceAction(AbstractCreature target, DamageInfo info, AbstractFrierenCard c) {
        this.info = info;
        this.c = c;
        setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.1F;
    }
    public void update() {
        if (this.duration == 0.1F && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.NONE));
            c.applyPowers();
            this.target.damage(this.info);
            this.addToBot(new GainBlockAction(AbstractDungeon.player,c.block));
            if(c.baseMagicNumber > 0)
                this.addToBot(new DrawCardAction(c.baseMagicNumber));
            if ((((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead &&
                    !this.target.hasPower("Minion")) {
                this.increaseEleganceMisc(c.uuid,2);
                if(((AbstractMonster) this.target).type == AbstractMonster.EnemyType.ELITE || ((AbstractMonster) this.target).type == AbstractMonster.EnemyType.BOSS)
                    this.increaseEleganceSecondMisc(c.uuid,1);
            }
            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead())
                AbstractDungeon.actionManager.clearPostCombatActions();
        }
        this.tickDuration();
    }
    private void increaseEleganceMisc(UUID targetUUID, int miscIncrease){
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (!c.uuid.equals(targetUUID))
                continue;
            c.misc += miscIncrease;
            c.baseDamage += c.misc;
            c.baseBlock += c.misc;
            c.isDamageModified = false;
            c.isBlockModified = false;
        }
        for (AbstractCard c : GetAllInBattleInstances.get(targetUUID)) {
            c.misc += miscIncrease;
            c.baseDamage += c.misc;
            c.baseBlock += c.misc;
        }
    }
    private void increaseEleganceSecondMisc(UUID targetUUID, int miscIncrease){
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if(c instanceof AbstractFrierenCard){
                if (!c.uuid.equals(targetUUID))
                    continue;
                ((AbstractFrierenCard) c).secondMisc += miscIncrease;
                c.baseMagicNumber = ((AbstractFrierenCard) c).secondMisc;
                c.isMagicNumberModified = false;
            }
        }
        for (AbstractCard c : GetAllInBattleInstances.get(targetUUID)) {
            if(c instanceof AbstractFrierenCard){
                ((AbstractFrierenCard) c).secondMisc += miscIncrease;
                c.baseMagicNumber = ((AbstractFrierenCard) c).secondMisc;
            }
        }
    }
}

