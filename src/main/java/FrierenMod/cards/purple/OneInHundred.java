package FrierenMod.cards.purple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.SpecializedOffensiveMagic;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.ActionHelper;
import FrierenMod.gameHelpers.CardPoolHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OneInHundred extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(OneInHundred.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 3, CardType.SKILL, CardEnums.FERN_CARD, CardRarity.UNCOMMON, CardTarget.SELF);

    public OneInHundred() {
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
        ActionHelper.addToBotAbstract(() -> {
            int times = 1;
            times += (int) p.exhaustPile.group.stream().filter(card -> card.cardID.equals(SpecializedOffensiveMagic.ID)).count();
            for (int i = 0; i < times * 2; i++) {
                AbstractCard c = CardPoolHelper.getRandomCard(CardPoolHelper.PoolType.FUSION);
                c.setCostForTurn(0);
                this.addToBot(new MakeTempCardInHandAction(c));
            }
        });
    }
}

