package FrierenMod.cards.purple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.powers.ConcentrationPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Moody extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(Moody.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final CardInfo info = new CardInfo(ID, 2, CardType.ATTACK, CardEnums.FERN_CARD, CardRarity.UNCOMMON, CardTarget.ENEMY);

    public Moody() {
        super(info);
        this.baseDamage = 0;
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void initSpecifiedAttributes() {
        this.damage = this.baseDamage = 6;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ConcentrationPower po = (ConcentrationPower) AbstractDungeon.player.getPower(ConcentrationPower.POWER_ID);
        this.baseDamage = po == null ? 0 : po.changeTimes;
        this.calculateCardDamage(null);
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    public void applyPowers() {
        ConcentrationPower po = (ConcentrationPower) AbstractDungeon.player.getPower(ConcentrationPower.POWER_ID);
        int count = po == null ? 0 : po.changeTimes;
        if (count > 0) {
            this.baseDamage = count;
            super.applyPowers();
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }
}