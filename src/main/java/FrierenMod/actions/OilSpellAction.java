package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

public class OilSpellAction extends AbstractGameAction {
    private final AbstractCard c;

    public OilSpellAction(AbstractCard c) {
        this.c = c;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new SFXAction("ATTACK_HEAVY"));
        this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        c.applyPowers();
        int flag = 0;
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
            if(!mo.isDead && !mo.halfDead){
                if (mo.currentHealth - c.damage <= 0) {
                    flag = 1;
                    break;
                }
            }
        }
        this.addToBot(new DamageAllEnemiesAction(p, c.damage, c.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        if(flag == 0){
            this.addToBot(new DamageAllEnemiesAction(p, c.damage, c.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        }
        this.isDone = true;
    }
}
