package FrierenMod.cards;

import FrierenMod.gameHelpers.ChantHelper;
import FrierenMod.gameHelpers.LegendMagicHelper;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FrierenMod.Characters.Frieren.Enums.FRIEREN_CARD;

public abstract class AbstractFrierenCard extends CustomCard {
    public boolean isChantCard;
    public boolean isChantUpgraded;
    public boolean isMagicPower;
    public boolean isFinalMagicPower;
    public boolean isFastMagicPower;
    public boolean isLegendMagicCard;
    public int baseChantX = -1;
    public int chantX = -1;
    public boolean isChantXModified;
    public boolean upgradedChantX;
    public int secondMagicNumber = -1;
    public int baseSecondMagicNumber = -1;
    public boolean isSecondMagicNumberModified;
    public boolean upgradedSecondMagicNumber;
    public boolean isUsingMagicPower;
    public boolean isMagicSource;
    public boolean canGainMagic;
    public AbstractFrierenCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        initSwitches();
    }
    public AbstractFrierenCard(String id, String rawDescription, CardType type, CardTarget target) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, ModInformation.makeCardImgPath(id.split(":")[1]), -2, rawDescription, type, CardColor.COLORLESS, CardRarity.SPECIAL, target);
        initSwitches();
    }
    public AbstractFrierenCard(String id, String img, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, img, cost, CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, type, color, rarity, target);
        initSwitches();
    }
    public AbstractFrierenCard(String id, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, ModInformation.makeCardImgPath(id.split(":")[1]), cost, CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, type, color, rarity, target);
        initSwitches();
    }
    public AbstractFrierenCard(String id, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, ModInformation.makeCardImgPath(id.split(":")[1]), cost, CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, type, FRIEREN_CARD, rarity, target);
        initSwitches();
    }
    public AbstractFrierenCard(String id, int cost, CardType type, CardRarity rarity) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, ModInformation.makeCardImgPath(id.split(":")[1]), cost, CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, type, FRIEREN_CARD, rarity, CardTarget.NONE);
        initSwitches();
    }
    public AbstractFrierenCard(String id, int cost, CardRarity rarity) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, ModInformation.makeCardImgPath(id.split(":")[1]), cost, CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, CardType.SKILL, FRIEREN_CARD, rarity, CardTarget.NONE);
        initSwitches();
    }
    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }
    private void initSwitches(){
        this.isCostModified = false;
        this.isCostModifiedForTurn = false;
        this.isDamageModified = false;
        this.isBlockModified = false;
        this.isMagicNumberModified = false;
        this.isChantCard = false;
        this.isLegendMagicCard = false;
        this.isChantXModified = false;
        this.isMagicPower = false;
        this.isFinalMagicPower = false;
        this.isFastMagicPower = false;
        this.isSecondMagicNumberModified = false;
        this.upgradedSecondMagicNumber = false;
        this.isChantUpgraded = false;
        this.isUsingMagicPower = false;
        this.isMagicSource = false;
        this.canGainMagic = true;
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
        if(this.isChantCard && !this.isLegendMagicCard && !isChantUpgraded){
            return canChantCardUse(m);
        }
        else if(this.isLegendMagicCard && !this.isChantCard){
            return canLegendMagicCardUse(m);
        } else if (this.isChantCard && this.isLegendMagicCard && !isChantUpgraded) {
            return canLegendMagicCardUse(m) && canChantCardUse(m);
        }else {
            return upgradedCanUse(p,m);
        }
    }
    public boolean upgradedCanUse(AbstractPlayer p, AbstractMonster m){
        if (this.type == AbstractCard.CardType.STATUS && this.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Medical Kit") && !this.isMagicPower) {
            return false;
        } else if (this.type == AbstractCard.CardType.CURSE && this.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Blue Candle")) {
            return false;
        } else if (p.hasPower("FrierenMod:ImaginationPower") && ChantHelper.getAllMagicPowerNum() < this.cost){
            return false;
        }
        else {
            return this.cardPlayable(m) && this.hasEnoughEnergy();
        }
    }
    private boolean canChantCardUse(AbstractMonster m){
        return ChantHelper.canChantUse(this,m,this.chantX);
    }
    private boolean canLegendMagicCardUse(AbstractMonster m){
        return LegendMagicHelper.canLegendMagicUse(this,m);
    }
}
