package FrierenMod.cards.purple;

import FrierenMod.actions.DrawCardByTagAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RaidPreparation extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(RaidPreparation.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.SKILL, CardEnums.FERN_CARD, CardRarity.COMMON, CardTarget.SELF);

    public RaidPreparation() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.raidNumber = this.baseRaidNumber = 3;
        this.tags.add(Enum.RAID);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeRaidNumber(-1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardByTagAction(1, Enum.RAID));
        this.isRaidTriggered = CombatHelper.triggerRaid(raidNumber, () -> this.addToBot(new DrawCardByTagAction(1, Enum.FUSION)));
    }
}

