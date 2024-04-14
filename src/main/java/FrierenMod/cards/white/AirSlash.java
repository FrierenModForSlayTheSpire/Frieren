package FrierenMod.cards.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.powers.AccelerateFlowPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.powers.BanManaGainPower;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AirSlash extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(AirSlash.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.ATTACK, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON, CardTarget.ENEMY);

    public AirSlash() {
        super(info);
    }

//    public AirSlash(CardColor color) {
//        super(ID, 2, CardType.ATTACK, color, CardRarity.UNCOMMON, CardTarget.ENEMY);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.damage = this.baseDamage = 8;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        this.addToBot(new ApplyPowerAction(p,p,new AccelerateFlowPower(p)));
        this.addToBot(new ApplyPowerAction(p,p,new BanManaGainPower(p)));
    }
}
