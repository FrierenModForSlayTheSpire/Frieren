package FrierenMod.cards;

import FrierenMod.helpers.ChantHelper;
import FrierenMod.helpers.LegendMagicHelper;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class AbstractFrierenCard extends CustomCard {
    public boolean isChantCard;
    public boolean isMagicPower;
    public boolean isLegendMagicCard;
    public int baseChantX = -1;
    public int chantX = -1;
    public boolean isChantXModified;
    public boolean upgradedChantX;
    public int secondMagicNumber = -1;
    public int baseSecondMagicNumber = -1;
    public boolean isSecondMagicNumberModified;
    public boolean upgradedSecondMagicNumber;
    public AbstractFrierenCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.isCostModified = false;
        this.isCostModifiedForTurn = false;
        this.isDamageModified = false;
        this.isBlockModified = false;
        this.isMagicNumberModified = false;
        this.isChantCard = false;
        this.isLegendMagicCard = false;
        this.isChantXModified = false;
        this.isMagicPower = false;
        this.isSecondMagicNumberModified = false;
        this.upgradedSecondMagicNumber = false;
    }
    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }
    public void upgradeChantX(int amount){
        this.baseChantX += amount;
        this.chantX = this.baseChantX;
        this.upgradedChantX = true;
    }
    public void upgradeSecondMagicNumber(int amount){
        this.baseSecondMagicNumber += amount;
        this.secondMagicNumber = this.baseSecondMagicNumber;
        this.upgradedSecondMagicNumber = true;
    }
    public void displayUpgrades() {
        super.displayUpgrades();
        if(this.upgradedChantX){
            this.chantX = this.baseChantX;
            this.isChantXModified = true;
        }
        if(this.upgradedSecondMagicNumber){
            this.secondMagicNumber = this.baseSecondMagicNumber;
            this.isSecondMagicNumberModified = true;
        }
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if(this.isChantCard && !this.isLegendMagicCard){
            return canChantCardUse(m);
        }
        else if(this.isLegendMagicCard && !this.isChantCard){
            return canLegendMagicCardUse(m);
        } else if (this.isChantCard && this.isLegendMagicCard) {
            return canLegendMagicCardUse(m) && canChantCardUse(m);
        }else {
            return super.canUse(p,m);
        }
    }
    private boolean canChantCardUse(AbstractMonster m){
        return new ChantHelper().canChantUse(this,m,this.chantX);
    }
    private boolean canLegendMagicCardUse(AbstractMonster m){
        return new LegendMagicHelper().canLegendMagicUse(this,m);
    }
}
