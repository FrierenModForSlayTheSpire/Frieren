package FrierenMod.cards.white;

import FrierenMod.actions.FlowAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Flow extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(Flow.class.getSimpleName());
    public Flow() {
        super(ID, 0, CardRarity.COMMON);
        this.magicNumber = this.baseMagicNumber = 3;
        this.cardsToPreview = new MagicPower();
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new FlowAction(this.magicNumber));
    }
}
