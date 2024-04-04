package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FrierenMod.gameHelpers.ChantHelper.getManaNumInDiscardPile;
import static FrierenMod.gameHelpers.ChantHelper.getManaNumInDrawPile;

public class LockTargetAction extends AbstractGameAction {
    private final int magicNumber;
    private final int damage;
    private final DamageInfo.DamageType damageTypeForTurn;
    private final AbstractMonster m;

    public LockTargetAction(int magicNumber, int damage, DamageInfo.DamageType damageTypeForTurn, AbstractMonster m) {
        this.magicNumber = magicNumber;
        this.damage = damage;
        this.damageTypeForTurn = damageTypeForTurn;
        this.m = m;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new DamageAction(m, new DamageInfo( p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        this.addToBot(new DrawCardAction(this.magicNumber));
        if (getManaNumInDrawPile()>=getManaNumInDiscardPile()) {
            this.addToBot(new DamageAction(m, new DamageInfo( p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            this.addToBot(new DrawCardAction(this.magicNumber));
        }
        this.isDone =true;
    }
}
