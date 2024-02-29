package FrierenMod.cards.white.chant;

import FrierenMod.actions.ChantAction;
import FrierenMod.actions.MakeManaInDrawPileAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.gameHelpers.LegendMagicHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ContinualChant extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(ContinualChant.class.getSimpleName());
    public ContinualChant() {
        super(ID, 0, CardRarity.UNCOMMON);
        this.chantX = this.baseChantX = 4;
        this.magicNumber = this.baseMagicNumber = 4;
        this.cardsToPreview = new Mana();
        this.isChantCard = true;
        this.isLegendaryMagic = true;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.upgradeChantX(1);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MakeManaInDrawPileAction(this.magicNumber));
        this.addToBot(new ChantAction(this.chantX));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if(!LegendMagicHelper.canLegendMagicUse(this,m)){
            return false;
        }
        return super.canUseOriginally(p,m);
    }
}
