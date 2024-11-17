package FrierenMod.cards.whitePurple;

import FrierenMod.actions.MakeManaInHandAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.powers.AccelerateFlowPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ClearMind extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(ClearMind.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON);
    public static final CardInfo info2 = new CardInfo(ID, 2, CardEnums.FERN_CARD, CardRarity.UNCOMMON, true);

    public ClearMind() {
        super(info);
    }

    public ClearMind(boolean forSecondRegister) {
        super(info2);
    }

    @Override
    public AbstractCard getCardForSecondRegister() {
        return new ClearMind(true);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.tags.add(Enum.FRIEREN_FERN_CARD);
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
