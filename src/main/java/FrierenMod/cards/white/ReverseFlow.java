package FrierenMod.cards.white;

import FrierenMod.actions.DrawMagicFromDiscardPileAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ReverseFlow extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(ReverseFlow.class.getSimpleName());
    public ReverseFlow() {
        super(ID, 1, CardRarity.COMMON);
        this.magicNumber = this.baseMagicNumber = 4;
        this.cardsToPreview = new MagicPower();
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
        this.addToBot(new DrawMagicFromDiscardPileAction(this.magicNumber));
    }
}
