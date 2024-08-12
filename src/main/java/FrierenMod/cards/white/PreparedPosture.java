package FrierenMod.cards.white;

import FrierenMod.actions.DrawChantAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PreparedPosture extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(PreparedPosture.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.ATTACK, CardEnums.FRIEREN_CARD, CardRarity.COMMON, CardTarget.ENEMY);

    public PreparedPosture() {
        super(info);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.damage = this.baseDamage = 6;
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
        this.addToBot(new DrawChantAction(1));
    }
}
