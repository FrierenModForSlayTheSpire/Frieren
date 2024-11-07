package FrierenMod.cards.whitePurple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.powers.ManaReturnPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ManaDetection extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(ManaDetection.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.ATTACK, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON, CardTarget.ENEMY);
    public static final CardInfo info2 = new CardInfo(ID, 1, CardType.ATTACK, CardEnums.FERN_CARD, CardRarity.UNCOMMON, CardTarget.ENEMY, true);

    public ManaDetection() {
        super(info);
    }

    public ManaDetection(boolean forSecondRegister) {
        super(info2);
    }

    @Override
    public AbstractCard getCardForSecondRegister() {
        return new ManaDetection(true);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.tags.add(Enum.FRIEREN_FERN_CARD);
        this.damage = this.baseDamage = 5;
        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(6);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        this.addToBot(new ApplyPowerAction(m, p, new ManaReturnPower(m, this.magicNumber), this.magicNumber));
    }
}