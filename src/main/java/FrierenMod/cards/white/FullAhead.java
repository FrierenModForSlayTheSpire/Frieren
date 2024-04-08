package FrierenMod.cards.white;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.powers.FullAheadPower;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FullAhead extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(FullAhead.class.getSimpleName());
    public FullAhead() {
        super(ID, 2,CardType.POWER, CardRarity.COMMON);
        this.magicNumber = this.baseMagicNumber = 2;
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
        this.addToBot(new ApplyPowerAction(p, p, new FullAheadPower(p,this.magicNumber)));
    }
}
