package FrierenMod.cards.white;

import FrierenMod.actions.MakeManaInHandAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.powers.AccelerateFlowPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ClearMind extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(ClearMind.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON);

    public ClearMind() {
        super(info);
    }

//    public ClearMind(CardColor color) {
//        super(ID, 2, color, CardRarity.UNCOMMON);
//    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MakeManaInHandAction(this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new AccelerateFlowPower(p)));
    }
}
