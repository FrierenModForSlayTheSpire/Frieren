package FrierenMod.cards.white;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.gameHelpers.ChantHelper;
import FrierenMod.powers.PajamasFormPower;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PajamasForm extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(PajamasForm.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public PajamasForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE);
        this.baseDamage = 0;
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new Mana();
    }
    public void calculateCardDamage(AbstractMonster mo) {
        this.baseMagicNumber = ChantHelper.getAllMagicPowerNum() *3;
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
        this.baseMagicNumber = ChantHelper.getAllMagicPowerNum() *3;
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
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.damage += this.magicNumber;
        this.addToBot(new ApplyPowerAction(p, p, new PajamasFormPower(p,1)));
    }
}
