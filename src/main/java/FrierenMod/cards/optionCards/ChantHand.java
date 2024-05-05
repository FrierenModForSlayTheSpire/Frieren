package FrierenMod.cards.optionCards;

import FrierenMod.actions.ChantFromHandAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ChantHand extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(ChantHand.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.SKILL, CardTarget.NONE);
    public static final CardInfo info2 = new CardInfo(ID, -2, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    public AbstractCard cardToReturn;
    public AbstractGameAction[] nextAction;

    public ChantHand() {
        super(info);
        this.cardsToPreview = new Mana();
    }
    public ChantHand(int manaExhaust, int reward) {
        super(info2);
        this.secondMagicNumber = this.baseSecondMagicNumber = manaExhaust;
        this.magicNumber = this.baseMagicNumber = reward;
        this.isSecondMagicNumberModified = (manaExhaust < reward);
        this.cardToReturn = null;
        this.nextAction = null;
    }

    public ChantHand(int manaExhaust, int reward, AbstractCard cardToReturn, AbstractGameAction... nextAction) {
        super(info2);
        this.secondMagicNumber = this.baseSecondMagicNumber = manaExhaust;
        this.magicNumber = this.baseMagicNumber = reward;
        this.isSecondMagicNumberModified = (manaExhaust < reward);
        this.cardToReturn = cardToReturn;
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
        if (cardToReturn != null) {
            cardToReturn.returnToHand = true;
            cardToReturn.upgrade();
        }
        this.addToBot(new ChantFromHandAction(this.secondMagicNumber, this.magicNumber, nextAction));
    }
}
