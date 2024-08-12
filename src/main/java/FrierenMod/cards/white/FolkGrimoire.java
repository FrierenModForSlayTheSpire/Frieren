package FrierenMod.cards.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.ActionHelper;
import FrierenMod.gameHelpers.CardPoolHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FolkGrimoire extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(FolkGrimoire.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON);

    public FolkGrimoire() {
        super(info);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.exhaust = true;
        this.magicNumber = baseMagicNumber = 1;
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
        ActionHelper.addToBotAbstract(() -> {
            AbstractCard c1 = CardPoolHelper.getRandomCard(CardPoolHelper.PoolType.CHANT);
            AbstractCard c2 = CardPoolHelper.getRandomCard(CardPoolHelper.PoolType.LEGENDARY_SPELL);
            c1.setCostForTurn(c1.cost - magicNumber);
            c2.setCostForTurn(c2.cost - magicNumber);
            this.addToBot(new MakeTempCardInHandAction(c1));
            this.addToBot(new MakeTempCardInHandAction(c2));
        });
    }
}
