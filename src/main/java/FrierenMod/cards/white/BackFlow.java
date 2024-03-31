package FrierenMod.cards.white;

import FrierenMod.actions.DrawMagicFromDiscardPileAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BackFlow extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(BackFlow.class.getSimpleName());
    public BackFlow() {
        super(ID, 1, CardRarity.COMMON);
        this.magicNumber = this.baseMagicNumber = 4;
        this.cardsToPreview = new Mana();
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
