package FrierenMod.cards.canAutoAdd.white;

import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.powers.GainRelicPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AccessoriesSpell extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(AccessoriesSpell.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 4, CardType.POWER, CardEnums.FRIEREN_CARD, CardRarity.RARE, CardTarget.NONE);
    public AccessoriesSpell() {
        super(info);
    }
    public void initSpecifiedAttributes() {
        this.isInnate = true;
        this.isEthereal = true;
        this.isLegendarySpell = true;
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
        this.addToBot(new ApplyPowerAction(p, p, new GainRelicPower(p, 1)));
    }
}
