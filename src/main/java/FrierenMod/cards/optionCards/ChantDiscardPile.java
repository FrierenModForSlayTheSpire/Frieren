package FrierenMod.cards.optionCards;

import FrierenMod.actions.ChantFromDiscardPileAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ChantDiscardPile extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(ChantDiscardPile.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.SKILL, CardTarget.NONE);
    public static final CardInfo info2 = new CardInfo(ID, -2, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    private AbstractGameAction[] nextAction;

    public ChantDiscardPile() {
        super(info);
        this.cardsToPreview = new Mana();
    }

    public ChantDiscardPile(int manaExhaust, int reward) {
        super(info2);
        this.secondMagicNumber = this.baseSecondMagicNumber = manaExhaust;
        this.magicNumber = this.baseMagicNumber = reward;
        this.isSecondMagicNumberModified = (manaExhaust < reward);
        this.nextAction = null;
    }

    public ChantDiscardPile(int manaExhaust, int reward, AbstractGameAction... nextAction) {
        super(info2);
        this.secondMagicNumber = this.baseSecondMagicNumber = manaExhaust;
        this.magicNumber = this.baseMagicNumber = reward;
        this.isSecondMagicNumberModified = (manaExhaust < reward);
        this.nextAction = nextAction;
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }

    public void onChoseThisOption() {
        this.addToBot(new ChantFromDiscardPileAction(this.secondMagicNumber, this.magicNumber, this.nextAction));
    }
}
