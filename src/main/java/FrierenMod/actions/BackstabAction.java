package FrierenMod.actions;

import FrierenMod.powers.ConcentrationPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class BackstabAction extends AbstractGameAction {
    private boolean upgraded;
    public int[] damage;

    private int baseDamage;
    private int secondBaseDamage;

    private boolean firstFrame = true, utilizeBaseDamage = false;

    public BackstabAction(AbstractCreature source, int[] amount, DamageInfo.DamageType type, AbstractGameAction.AttackEffect effect, boolean isFast) {
        this.source = source;
        this.damage = amount;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.damageType = type;
        this.attackEffect = effect;
        if (isFast) {
            this.duration = Settings.ACTION_DUR_XFAST;
        } else {
            this.duration = Settings.ACTION_DUR_FAST;
        }
    }

    public BackstabAction(AbstractPlayer player, int baseDamage, DamageInfo.DamageType type, AbstractGameAction.AttackEffect effect, boolean upgraded) {
        this(player, null, type, effect, false);
        this.baseDamage = baseDamage;
        this.upgraded = upgraded;
        this.utilizeBaseDamage = true;
    }

    public void update() {
        if (this.firstFrame) {
            boolean playedMusic = false;
            int temp = (AbstractDungeon.getCurrRoom()).monsters.monsters.size();
            if (this.utilizeBaseDamage)
                this.damage = DamageInfo.createDamageMatrix(this.baseDamage);
            for (int i = 0; i < temp; i++) {
                if (!(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).isDying &&
                        (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).currentHealth > 0 &&
                        !(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).isEscaping)
                    if (playedMusic) {
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect(

                                (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).hb.cX,
                                (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).hb.cY, this.attackEffect, true));
                    } else {
                        playedMusic = true;
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect(

                                (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).hb.cX,
                                (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i).hb.cY, this.attackEffect));
                    }
            }
            this.firstFrame = false;
        }
        tickDuration();
        if (this.isDone) {
            for (AbstractPower p : AbstractDungeon.player.powers)
                p.onDamageAllEnemies(this.damage);
            int temp = (AbstractDungeon.getCurrRoom()).monsters.monsters.size();
            boolean reward = true;
            for (int i = 0; i < temp; i++) {
                AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                if (!target.isDeadOrEscaped()) {
                    target.damage(new DamageInfo(this.source, this.damage[i], this.damageType));
                    if (target.isDying || target.currentHealth <= 0) {
                        reward = false;
                    }
                }
            }
            if (reward) {
                this.addToBot(new GainEnergyAction(1));
                if (upgraded) {
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ConcentrationPower(3)));
                }
            }
            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead())
                AbstractDungeon.actionManager.clearPostCombatActions();
            if (!Settings.FAST_MODE)
                addToTop(new WaitAction(0.1F));
        }
    }
}
