package FrierenMod.cards.white.chant;

import FrierenMod.actions.ChantAction;
import FrierenMod.actions.RustCleanMagicAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.gameHelpers.ChantHelper;
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
            this.upgradeMagicNumber(-4);
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        if (ChantHelper.getAllManaNum() > 10)
            this.glowColor = GOLD_BORDER_GLOW_COLOR;
        else
            this.glowColor = BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChantAction(this.chantX, new RustCleanMagicAction(m, p, this.magicNumber)));
    }
}
