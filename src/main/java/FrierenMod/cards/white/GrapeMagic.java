package FrierenMod.cards.white;

import FrierenMod.actions.MakeMagicPowerInHandAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GrapeMagic extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(GrapeMagic.class.getSimpleName());
    public GrapeMagic() {
        super(ID, 1, CardRarity.UNCOMMON);
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ExhaustAction(2,false,false,false));
        this.addToBot(new MakeMagicPowerInHandAction(2));
    }
}
