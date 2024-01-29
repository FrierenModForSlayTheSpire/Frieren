package FrierenMod.cards.white;
import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.helpers.ChantHelper;
import FrierenMod.helpers.ModHelper;
import FrierenMod.powers.ShuiYiXingTaiPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FrierenMod.Characters.Frieren.Enums.FRIEREN_CARD;

public class ShuiYiXingTai extends CustomCard{
    public static final String ID = ModHelper.makePath(ShuiYiXingTai.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "FrierenModResources/img/cards/Strike.png";
    private static final int COST = 3;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = FRIEREN_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.NONE;
    public ShuiYiXingTai() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 0;
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new MagicPower();
    }
    public void calculateCardDamage(AbstractMonster mo) {
        this.baseMagicNumber = new ChantHelper().getAllMagicPowerNum() *3;
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.baseMagicNumber;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        this.initializeDescription();
    }
    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseMagicNumber = new ChantHelper().getAllMagicPowerNum() *3;
        this.baseDamage += this.baseMagicNumber;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        this.initializeDescription();
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.damage += this.magicNumber;
        this.addToBot(new ApplyPowerAction(p, p, new ShuiYiXingTaiPower(p,1)));
    }
}
