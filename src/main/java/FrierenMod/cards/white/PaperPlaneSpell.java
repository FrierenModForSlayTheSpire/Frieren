package FrierenMod.cards.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.powers.PaperPlaneSpellPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@AutoAdd.Ignore
public class PaperPlaneSpell extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(PaperPlaneSpell.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardType.POWER, CardEnums.FRIEREN_CARD, CardRarity.RARE, CardTarget.NONE);

    public PaperPlaneSpell() {
        super(info);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.tags.add(Enum.LEGENDARY_SPELL);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new PaperPlaneSpellPower(1)));
    }
}
