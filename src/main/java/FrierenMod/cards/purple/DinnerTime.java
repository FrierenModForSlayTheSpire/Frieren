package FrierenMod.cards.purple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.GreenApple;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;

public class DinnerTime extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(DinnerTime.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.SKILL, CardEnums.FERN_CARD, CardRarity.UNCOMMON, CardTarget.SELF);

    public DinnerTime() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.tags.add(Enum.SPEED);
        this.magicNumber = this.baseMagicNumber = 2;
        this.raidNumber = this.baseRaidNumber = 1;
        this.tags.add(Enum.RAID);
        this.cardsToPreview = new GreenApple();
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
        this.addToBot(new MakeTempCardInHandAction(new GreenApple()));
        this.isRaidTriggered = CombatHelper.triggerRaid(raidNumber, () -> {
            this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber)));
            this.addToBot(new ApplyPowerAction(p, p, new LoseDexterityPower(p, this.magicNumber)));
        });
    }
}

