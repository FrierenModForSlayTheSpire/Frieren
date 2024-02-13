package FrierenMod.cards.white.chant;

import FrierenMod.actions.ChantAction;
import FrierenMod.actions.StatueMagicAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RustCleanMagic extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(RustCleanMagic.class.getSimpleName());
    public RustCleanMagic() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = 10;
        this.chantX = this.baseChantX = 1;
        this.isChantCard = true;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(-5);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChantAction(this.isChantUpgraded,this.chantX));
        this.addToBot(new StatueMagicAction(m,p,this.magicNumber));
    }
}
