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
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OneInHundred extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(OneInHundred.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.SKILL, CardEnums.FERN_CARD, CardRarity.UNCOMMON, CardTarget.SELF);

    public OneInHundred() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.magicNumber = this.baseMagicNumber = 2;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.actionManager.cardsPlayedThisCombat.stream().anyMatch(card -> card.cardID.equals(SpecializedOffensiveMagic.ID))) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR;
        } else
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ActionHelper.addToBotAbstract(() -> {
            for (int i = 0; i < this.magicNumber; i++) {
                AbstractCard c = CardPoolHelper.getRandomCard(CardPoolHelper.PoolType.FUSION);
                if (AbstractDungeon.actionManager.cardsPlayedThisCombat.stream().anyMatch(card -> card.cardID.equals(SpecializedOffensiveMagic.ID))) {
                    c.setCostForTurn(0);
                }
                this.addToBot(new MakeTempCardInHandAction(c));
            }
        });
    }
}

