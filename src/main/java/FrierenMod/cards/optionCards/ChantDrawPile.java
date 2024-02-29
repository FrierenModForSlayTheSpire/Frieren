package FrierenMod.cards.optionCards;

import FrierenMod.actions.ChantFromDrawPileAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ChantDrawPile extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(ChantDrawPile.class.getSimpleName());
    public ChantDrawPile() {
        super(ID, -2, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.cardsToPreview = new Mana();
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
        this.addToBot(new ChantFromDrawPileAction(this.block,this.magicNumber));
    }
}
