package FrierenMod.cards.white;

import FrierenMod.actions.RedAppleMagicAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.cards.tempCards.GreenApple;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RedAppleSpell extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(RedAppleSpell.class.getSimpleName());
    public RedAppleSpell() {
        super(ID, 1, CardRarity.UNCOMMON);
        this.cardsToPreview = new GreenApple();
        this.selfRetain = true;
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
