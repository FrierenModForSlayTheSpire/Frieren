package FrierenMod.cards.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.SlotBgHelper;
import FrierenMod.powers.GainRelicPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AccessoriesSpell extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(AccessoriesSpell.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 4, CardType.POWER, CardEnums.FRIEREN_CARD, CardRarity.RARE, CardTarget.NONE);

    public AccessoriesSpell() {
        super(info);
    }

    public void initSpecifiedAttributes() {
        this.isInnate = true;
        this.isEthereal = true;
        this.tags.add(AbstractBaseCard.Enum.LEGENDARY_SPELL);
        this.tags.add(Enum.CAN_NOT_RANDOM_GENERATED_IN_COMBAT);
        this.tags.add(CardTags.HEALING);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        SlotBgHelper.unlockANewSlot("4010");
        this.addToBot(new ApplyPowerAction(p, p, new GainRelicPower(p, 1)));
    }
}
