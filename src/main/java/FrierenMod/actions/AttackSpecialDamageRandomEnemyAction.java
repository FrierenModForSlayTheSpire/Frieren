package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

public class AttackSpecialDamageRandomEnemyAction extends AbstractGameAction {
    private final int damage;

    public AttackSpecialDamageRandomEnemyAction(int damage) {
        this.damage = damage;
    }

    public void update() {
        this.target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        if (this.target != null) {
            target.damage(new DamageInfo(this.source, createDamageMatrix(damage, (AbstractMonster) target), DamageInfo.DamageType.THORNS));
            this.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));
            this.addToTop(new VFXAction(new LightningEffect(this.target.hb.cX, this.target.hb.cY)));
        }
        this.isDone = true;
    }

    private static int createDamageMatrix(int baseDamage, AbstractMonster mo) {
        DamageInfo info = new DamageInfo(AbstractDungeon.player, baseDamage);
        info.applyEnemyPowersOnly(mo);
        return info.output;
    }
}
