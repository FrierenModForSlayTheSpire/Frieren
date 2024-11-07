package FrierenMod.cards.purple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Sit extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(Sit.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final CardInfo info = new CardInfo(ID, 3, CardType.ATTACK, CardEnums.FERN_CARD, CardRarity.RARE, CardTarget.ENEMY);

    public Sit() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.damage = this.baseDamage = 12;
        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(-1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = 1 + CombatHelper.getConcentrationPowerAmt() / magicNumber;
        for (int i = 0; i < amount; i++) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int amount = 1 + CombatHelper.getConcentrationPowerAmt() / magicNumber;
        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.DESCRIPTION;
        if (amount > 0)
            this.rawDescription += String.format(cardStrings.EXTENDED_DESCRIPTION[0], amount);
        initializeDescription();
    }

    public void applyPowers() {
        int amount = 1 + CombatHelper.getConcentrationPowerAmt() / magicNumber;
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION;
        if (amount > 0)
            this.rawDescription += String.format(cardStrings.EXTENDED_DESCRIPTION[0], amount);
        initializeDescription();
    }
}