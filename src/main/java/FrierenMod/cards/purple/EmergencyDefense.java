package FrierenMod.cards.purple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EmergencyDefense extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(EmergencyDefense.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 0, CardType.SKILL, CardEnums.FERN_CARD, CardRarity.COMMON, CardTarget.SELF);

    public EmergencyDefense() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.block = baseBlock = 10;
        this.raidNumber = baseRaidNumber = 1;
        this.tags.add(Enum.RAID);
        this.tags.add(Enum.SPEED);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        this.isRaidTriggered = CombatHelper.triggerRaid(raidNumber, () -> this.exhaust = false);
    }
}

