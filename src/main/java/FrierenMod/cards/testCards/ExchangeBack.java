package FrierenMod.cards.testCards;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.gameHelpers.ActionHelper;
import FrierenMod.gameHelpers.CharacterExchanger;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ExchangeBack extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(ExchangeBack.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 0, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    private CharacterExchanger exchanger;

    public ExchangeBack() {
        super(info);
    }

    public void setExchanger(CharacterExchanger exchanger){
        this.exchanger = exchanger;
    }

    @Override
    public void initSpecifiedAttributes() {
        this.selfRetain = true;
        this.exhaust = true;
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ActionHelper.addToBotAbstract(() -> {
            if (exchanger != null)
                exchanger.exchangeBack();
        });
    }
}
