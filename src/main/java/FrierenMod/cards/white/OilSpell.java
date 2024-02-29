package FrierenMod.cards.white;

import FrierenMod.actions.OilSpellAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OilSpell extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(OilSpell.class.getSimpleName());
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    public OilSpell() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.damage = this.baseDamage = 12;
        this.isMultiDamage = true;
        this.isLegendaryMagic = true;
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
        this.addToBot(new OilSpellAction(this));
    }
}
