package FrierenMod.cards.white;

import FrierenMod.actions.LightningMagicAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LightningMagic extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(LightningMagic.class.getSimpleName());
    public LightningMagic() {
        super(ID, 3, CardRarity.RARE);
        this.isLegendarySpell = true;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new LightningMagicAction());
    }
}
