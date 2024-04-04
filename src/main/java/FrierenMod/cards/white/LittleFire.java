package FrierenMod.cards.white;

import FrierenMod.actions.LittleFireAction;
import FrierenMod.actions.ModifyCostAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LittleFire extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(LittleFire.class.getSimpleName());
    public  LittleFire(){
     super(ID,0,CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
     this.damage = this.baseDamage = 3;
     this.magicNumber=this.baseMagicNumber=2;
     this.isCostResetCard = true;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null)
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
        addToBot(new LittleFireAction(this, this.magicNumber));
        this.addToBot(new ModifyCostAction(this.uuid,1));

    }
}