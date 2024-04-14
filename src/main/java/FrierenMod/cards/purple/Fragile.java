package FrierenMod.cards.purple;

import FrierenMod.actions.FragileAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Fragile extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(Fragile.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.SKILL, CardEnums.FERN_CARD, CardRarity.UNCOMMON, CardTarget.NONE);

    public Fragile() {
        super(info);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.raidNumber = this.baseRaidNumber = 5;
        this.magicNumber = this.baseMagicNumber = 2;
        this.returnToHand = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeRaidNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new FragileAction(this));
    }
}

