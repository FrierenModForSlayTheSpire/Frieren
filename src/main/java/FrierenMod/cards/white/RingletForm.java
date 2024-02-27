package FrierenMod.cards.white;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.powers.RingletFormPower;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RingletForm extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(RingletForm.class.getSimpleName());
    public RingletForm() {
        super(ID, 2, CardType.POWER, CardRarity.RARE);
        this.magicNumber = this.baseMagicNumber = 3;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
            this.upgradeMagicNumber(1);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new RingletFormPower(p,this.magicNumber)));
    }
}
