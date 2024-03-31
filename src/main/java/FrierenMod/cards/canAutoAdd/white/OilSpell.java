package FrierenMod.cards.canAutoAdd.white;

import FrierenMod.actions.OilSpellAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OilSpell extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(OilSpell.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardType.ATTACK, CardEnums.FRIEREN_CARD, CardRarity.COMMON, CardTarget.ALL_ENEMY);

    public OilSpell() {
        super(info);
    }

//    public OilSpell(CardColor color) {
//        super(ID, 2, CardType.ATTACK, color, CardRarity.COMMON, CardTarget.ALL_ENEMY);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.damage = this.baseDamage = 12;
        this.isLegendarySpell = true;
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
