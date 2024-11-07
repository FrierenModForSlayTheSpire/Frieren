package FrierenMod.cards.purple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.powers.ConcentrationPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TrainingByEating extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(TrainingByEating.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.SKILL, CardEnums.FERN_CARD, CardRarity.UNCOMMON, CardTarget.SELF);

    public TrainingByEating() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.raidNumber = this.baseRaidNumber = 6;
        this.tags.add(Enum.RAID);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            initializeDescription();
            this.exhaust = false;
        }
    }

    @Override
    public void applyPowers() {
        if (CombatHelper.canRaidTakeEffect(this.raidNumber)) {
            this.tags.add(Enum.SPEED);
        } else {
            this.tags.remove(Enum.SPEED);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.isRaidTriggered = CombatHelper.canRaidTakeEffect(this.raidNumber);
        if (!isRaidTriggered) {
            this.addToBot(new RemoveSpecificPowerAction(p, p, ConcentrationPower.POWER_ID));
        }
    }
}

