package FrierenMod.cards.canAutoAdd.optionCards;

import FrierenMod.actions.ChantFromHandAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.cards.canAutoAdd.tempCards.Mana;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ChantHand extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(ChantHand.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, -2, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    private AbstractCard cardToReturn;
    private AbstractGameAction[] nextAction;
    public ChantHand() {
        super(info);
        this.cardsToPreview = new Mana();
    }
    public ChantHand(AbstractCard cardToReturn) {
        super(info);
        this.cardsToPreview = new Mana();
        this.cardToReturn = cardToReturn;
    }
    public ChantHand(AbstractCard cardToReturn, AbstractGameAction... nextAction) {
        super(info);
        this.cardsToPreview = new Mana();
        this.cardToReturn = cardToReturn;
        this.nextAction = nextAction;
    }
    public void upgrade(){
        if(!this.upgraded){
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }
    public void onChoseThisOption() {
        if(cardToReturn != null){
            cardToReturn.returnToHand = true;
            cardToReturn.upgrade();
        }
        if(nextAction != null)
            this.addToBot(new ChantFromHandAction(this.magicNumber,nextAction));
        else {
            this.addToBot(new ChantFromHandAction(this.magicNumber));
        }
    }
}
