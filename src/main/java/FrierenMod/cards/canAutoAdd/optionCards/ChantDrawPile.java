package FrierenMod.cards.canAutoAdd.optionCards;

import FrierenMod.actions.ChantFromDrawPileAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.canAutoAdd.tempCards.Mana;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ChantDrawPile extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(ChantDrawPile.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, -2, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    private AbstractGameAction[] nextAction;
    public ChantDrawPile() {
        super(info);
        this.cardsToPreview = new Mana();
    }
    public ChantDrawPile(AbstractGameAction... nextAction) {
        super(info);
        this.cardsToPreview = new Mana();
        this.nextAction = nextAction;
    }
    public void upgrade(){
        if(!this.upgraded){
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;;
            this.initializeDescription();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }
    public void onChoseThisOption() {
        if(this.nextAction != null)
            this.addToBot(new ChantFromDrawPileAction(this.block,this.magicNumber,nextAction));
        else
            this.addToBot(new ChantFromDrawPileAction(this.block,this.magicNumber));
    }
}
