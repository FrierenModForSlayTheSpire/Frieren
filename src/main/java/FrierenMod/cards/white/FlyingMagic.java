package FrierenMod.cards.white;

import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

public class FlyingMagic extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(FlyingMagic.class.getSimpleName());

    public FlyingMagic() {
        super(ID, 2, CardType.POWER, CardEnums.FRIEREN_CARD, CardRarity.RARE);
    }

    public FlyingMagic(CardColor color) {
        super(ID, 2, CardType.POWER, color, CardRarity.RARE);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.magicNumber = this.baseMagicNumber = 2;
        this.isLegendarySpell = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, this.magicNumber), this.magicNumber));
    }
}
