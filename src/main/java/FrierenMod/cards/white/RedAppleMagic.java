package FrierenMod.cards.white;

import FrierenMod.actions.RedAppleMagicAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.tempCards.Apple;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RedAppleMagic extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(RedAppleMagic.class.getSimpleName());
    public RedAppleMagic() {
        super(ID, 1, CardRarity.UNCOMMON);
        this.cardsToPreview = new Apple();
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
        this.addToBot(new RedAppleMagicAction());
    }
}
