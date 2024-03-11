package FrierenMod.actions;

import FrierenMod.cards.AbstractFrierenCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class EleganceAction extends AbstractGameAction {
    private final DamageInfo info;

    private final AbstractFrierenCard c;
    public EleganceAction(AbstractCreature target, DamageInfo info, AbstractFrierenCard c) {
        this.info = info;
        this.c = c;
        setValues(target, info);
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_MED;
    }
    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED &&
                this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.NONE));
            c.applyPowers();
            this.target.damage(this.info);
            this.addToBot(new GainBlockAction(AbstractDungeon.player,c.block));
            if(c.baseMagicNumber > 0)
                this.addToBot(new DrawCardAction(c.magicNumber));
            if ((((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead &&
                    !this.target.hasPower("Minion")) {
                this.addToBot(new IncreaseEleganceMiscAction(c.uuid,c.misc,2));
                if(((AbstractMonster) this.target).type == AbstractMonster.EnemyType.ELITE || ((AbstractMonster) this.target).type == AbstractMonster.EnemyType.BOSS)
                    this.addToBot(new IncreaseEleganceSecondMiscAction(c.uuid,c.secondMisc,1));
            }
            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead())
                AbstractDungeon.actionManager.clearPostCombatActions();
        }
        tickDuration();
        if (this.isDone) {
            addToTop(new WaitAction(Settings.ACTION_DUR_MED));
        }
    }
}

