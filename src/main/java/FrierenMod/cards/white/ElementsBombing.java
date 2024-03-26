package FrierenMod.cards.white;

import FrierenMod.actions.ElementsBombingAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.tempCards.*;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ElementsBombing extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(ElementsBombing.class.getSimpleName());
    public AbstractFrierenCard constPre1 = new Ice();
    public AbstractFrierenCard constPre2 = new Fire();
    public AbstractFrierenCard constPre3 = new Thunder();
    public AbstractFrierenCard constPre4 = new Dark();
    public AbstractFrierenCard constPre5 = new Light();
    public ElementsBombing() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        this.exhaust = true;
        this.prev1 = this.constPre1;
        this.prev2 = this.constPre2;
        this.prev3 = this.constPre3;
        this.prev4 = this.constPre4;
        this.prev5 = this.constPre5;
        if(this.upgraded){
            this.prev1.upgrade();
            this.prev2.upgrade();
            this.prev3.upgrade();
            this.prev4.upgrade();
            this.prev5.upgrade();
        }
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
            this.constPre1.upgrade();
            this.constPre2.upgrade();
            this.constPre3.upgrade();
            this.constPre4.upgrade();
            this.constPre5.upgrade();
        }
    }
    public void hover(){
        this.prev1 = this.constPre1;
        this.prev2 = this.constPre2;
        this.prev3 = this.constPre3;
        this.prev4 = this.constPre4;
        this.prev5 = this.constPre5;
        super.hover();
    }
    public void unhover(){
        super.unhover();
        this.prev1 = null;
        this.prev2 = null;
        this.prev3 = null;
        this.prev4 = null;
        this.prev5 = null;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ElementsBombingAction(this.upgraded));
    }
}