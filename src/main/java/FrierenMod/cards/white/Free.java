package FrierenMod.cards.white;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FrierenMod.gameHelpers.ChantHelper.getAllMagicPowerNum;


public class Free extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(Free.class.getSimpleName());
    public Free() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.damage = this.baseDamage=20;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //this.addToBot(new FreeAction(this.magicNumber,this.damage,this.damageTypeForTurn,m));
        if(getAllMagicPowerNum()%2==0)
        {
            this.addToBot(new DamageAllEnemiesAction(p, this.baseDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            if(getAllMagicPowerNum()%3==0)
                this.addToBot(new DamageAllEnemiesAction(p, this.baseDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
        else
            this.addToBot(new DamageAllEnemiesAction(p, 10, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));

    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(5);
        }
    }

    public AbstractCard makeCopy() {
        return new Free();
    }
}
