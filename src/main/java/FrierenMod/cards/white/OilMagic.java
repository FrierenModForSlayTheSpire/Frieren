package FrierenMod.cards.white;

import FrierenMod.actions.OilMagicAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OilMagic extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(OilMagic.class.getSimpleName());
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    public OilMagic() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.damage = this.baseDamage = 12;
        this.isMultiDamage = true;
        this.isLegendMagicCard = true;
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
        this.addToBot(new OilMagicAction(this));
    }
}
