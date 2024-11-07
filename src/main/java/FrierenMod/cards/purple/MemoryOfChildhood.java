package FrierenMod.cards.purple;

import FrierenMod.actions.MemoryOfChildhoodAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MemoryOfChildhood extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(MemoryOfChildhood.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 3, CardType.SKILL, CardEnums.FERN_CARD, CardRarity.RARE, CardTarget.NONE);

    public MemoryOfChildhood() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.exhaust = true;
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
        this.addToBot(new MemoryOfChildhoodAction());
    }
}

