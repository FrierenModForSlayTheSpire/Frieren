package FrierenMod.cards.white;

import FrierenMod.actions.DrawManaFromDiscardPileAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.cards.canAutoAdd.tempCards.Mana;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BackFlow extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(BackFlow.class.getSimpleName());

    public BackFlow() {
        super(ID, 1, CardEnums.FRIEREN_CARD, CardRarity.COMMON);
    }

    public BackFlow(CardColor color) {
        super(ID, 1, color, CardRarity.COMMON);
    }

    @Override
    public void initSpecifiedAttributes() {
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
        this.addToBot(new DrawManaFromDiscardPileAction(this.magicNumber));
    }
}
