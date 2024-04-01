package FrierenMod.cards.canAutoAdd.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.canAutoAdd.tempCards.Mana;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.powers.PajamasFormPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PajamasForm extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(PajamasForm.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 3, CardType.POWER, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON);

    public PajamasForm() {
        super(info);
    }

//    public PajamasForm(CardColor color) {
//        super(ID, 3, CardType.POWER, color, CardRarity.UNCOMMON);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.baseDamage = 0;
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new Mana();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        this.baseMagicNumber = CombatHelper.getAllManaNum() * 3;
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.baseMagicNumber;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
        this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION;
        this.initializeDescription();
    }

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseMagicNumber = CombatHelper.getAllManaNum() * 3;
        this.baseDamage += this.baseMagicNumber;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
        this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION;
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
        this.addToBot(new ApplyPowerAction(p, p, new PajamasFormPower(p, 1)));
    }
}
