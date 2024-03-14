package FrierenMod.cards.optionCards;

import FrierenMod.actions.ChantFromHandAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.cards.white.chant.LureTheEnemyInDeep;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ChantHand extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(ChantHand.class.getSimpleName());
    private final boolean giveCard;
    public ChantHand() {
        super(ID, -2, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.cardsToPreview = new Mana();
        this.giveCard = false;
    }
    public ChantHand(boolean giveCard) {
        super(ID, -2, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.cardsToPreview = new Mana();
        this.giveCard = giveCard;
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
        if(this.giveCard){
            LureTheEnemyInDeep c = new LureTheEnemyInDeep();
            c.upgrade();
            c.upgraded = true;
            this.addToTop(new MakeTempCardInHandAction(c.makeStatEquivalentCopy()));
        }
        this.addToBot(new ChantFromHandAction(this.magicNumber));
    }
}
