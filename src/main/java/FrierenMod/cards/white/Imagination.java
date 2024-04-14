package FrierenMod.cards.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.powers.BanManaGainPower;
import FrierenMod.powers.ManaInsteadOfEnergyPower;
import FrierenMod.powers.ChantWithoutManaPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Imagination extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(Imagination.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardEnums.FRIEREN_CARD, CardRarity.RARE);

    public Imagination() {
        super(info);
    }

//    public Imagination(CardColor color) {
//        super(ID, 2, color, CardRarity.RARE);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.selfRetain = true;
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new ChantWithoutManaPower(p)));
        this.addToBot(new ApplyPowerAction(p, p, new BanManaGainPower(p)));
        this.addToBot(new ApplyPowerAction(p, p, new ManaInsteadOfEnergyPower(p)));
    }
}
