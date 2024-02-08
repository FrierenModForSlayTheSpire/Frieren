package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class SeeThroughPhantomAction extends AbstractGameAction {
    private final int magicNumber;
    private final int damage;
    private final DamageInfo.DamageType damageTypeForTurn;
    private final AbstractMonster m;

    public SeeThroughPhantomAction(int magicNumber, int damage, DamageInfo.DamageType damageTypeForTurn, AbstractMonster m) {
        this.magicNumber = magicNumber;
        this.damage = damage;
        this.damageTypeForTurn = damageTypeForTurn;
        this.m = m;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new DamageAction(m, new DamageInfo( p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

        if (m.getIntentBaseDmg()>=0)
            this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber));
        else
            this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
        this.isDone =true;
    }
}
