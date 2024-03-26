package FrierenMod.cards;

import FrierenMod.cards.tempCards.CustomLegendaryMagic;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.ChantHelper;
import FrierenMod.gameHelpers.LegendMagicHelper;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FrierenMod.gameHelpers.HardCodedPowerHelper.CHANT_WITHOUT_MANA;

public abstract class AbstractFrierenCard extends CustomCard {
    public boolean isChantCard;
    public boolean isMana;
    public boolean isLimitedOverMana;
    public boolean isAccelMana;
    public boolean isLegendaryMagic;
    public boolean isCostResetCard;
    public boolean isSealCard;
    public boolean isTaskCard;
    public int baseChantX = -1;
    public int chantX = -1;
    public boolean isChantXModified;
    public boolean upgradedChantX;
    public int secondMagicNumber = -1;
    public int baseSecondMagicNumber = -1;
    public int secondMisc = 0;
    public boolean isSecondMagicNumberModified;
    public boolean upgradedSecondMagicNumber;
    public int currentLevel = -1;
    public int currentLevelRequiredNumber = -1;
    public int currentInLevelProgressNumber = -1;
    public static final Color FLASH_COLOR = new Color(123.0F/255.0F,236.0F/255.0F,232.0F/255.0F,1.0F);
    public AbstractCard prev1;
    public AbstractCard prev2;
    public AbstractCard prev3;
    public AbstractCard prev4;
    public AbstractCard prev5;
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
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, ModInformation.makeCardImgPath(id.split(":")[1]), cost, CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, type, CardEnums.FRIEREN_CARD, rarity, target);
        initSwitches();
    }
    public AbstractFrierenCard(String id, int cost, CardType type, CardRarity rarity) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, ModInformation.makeCardImgPath(id.split(":")[1]), cost, CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, type, CardEnums.FRIEREN_CARD, rarity, CardTarget.NONE);
        initSwitches();
    }
    public AbstractFrierenCard(String id, int cost, CardRarity rarity) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, ModInformation.makeCardImgPath(id.split(":")[1]), cost, CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, CardType.SKILL, CardEnums.FRIEREN_CARD, rarity, CardTarget.NONE);
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
        this.isLegendaryMagic = false;
        this.isChantXModified = false;
        this.isMana = false;
        this.isLimitedOverMana = false;
        this.isAccelMana = false;
        this.isSecondMagicNumberModified = false;
        this.upgradedSecondMagicNumber = false;
        this.isCostResetCard = false;
        this.isSealCard = false;
        this.isTaskCard = false;
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
    public AbstractCard makeStatEquivalentCopy() {
        if(this instanceof CustomLegendaryMagic)
            return super.makeStatEquivalentCopy();
        AbstractCard card = this.makeCopy();
        card.name = this.name;
        card.target = this.target;
        card.upgraded = this.upgraded;
        card.timesUpgraded = this.timesUpgraded;
        card.baseDamage = this.baseDamage;
        card.baseBlock = this.baseBlock;
        card.baseMagicNumber = this.baseMagicNumber;
        card.cost = this.cost;
        card.costForTurn = this.costForTurn;
        card.isCostModified = this.isCostModified;
        card.isCostModifiedForTurn = this.isCostModifiedForTurn;
        card.inBottleLightning = this.inBottleLightning;
        card.inBottleFlame = this.inBottleFlame;
        card.inBottleTornado = this.inBottleTornado;
        card.isSeen = this.isSeen;
        card.isLocked = this.isLocked;
        card.misc = this.misc;
        card.freeToPlayOnce = this.freeToPlayOnce;
        if(card instanceof AbstractFrierenCard){
            ((AbstractFrierenCard) card).isChantCard = this.isChantCard;
            ((AbstractFrierenCard) card).isMana = this.isMana;
            ((AbstractFrierenCard) card).isLimitedOverMana = this.isLimitedOverMana;
            ((AbstractFrierenCard) card).isAccelMana = this.isAccelMana;
            ((AbstractFrierenCard) card).isLegendaryMagic = this.isLegendaryMagic;
            ((AbstractFrierenCard) card).isCostResetCard = this.isCostResetCard;
            ((AbstractFrierenCard) card).isSealCard = this.isSealCard;
            ((AbstractFrierenCard) card).baseChantX = this.baseChantX;
            ((AbstractFrierenCard) card).chantX = this.chantX;
            ((AbstractFrierenCard) card).isChantXModified = this.isChantXModified;
            ((AbstractFrierenCard) card).upgradedChantX = this.upgradedChantX;
            ((AbstractFrierenCard) card).secondMagicNumber = this.secondMagicNumber;
            ((AbstractFrierenCard) card).baseSecondMagicNumber = this.baseSecondMagicNumber;
            ((AbstractFrierenCard) card).secondMisc = this.secondMisc;
            ((AbstractFrierenCard) card).isSecondMagicNumberModified = this.isSecondMagicNumberModified;
            ((AbstractFrierenCard) card).upgradedSecondMagicNumber = this.upgradedSecondMagicNumber;
            if(this.isTaskCard){
                ((AbstractFrierenCard) card).updateDescriptionAndCardImg();
                ((AbstractFrierenCard) card).currentLevel = this.currentLevel;
                ((AbstractFrierenCard) card).currentInLevelProgressNumber = this.currentInLevelProgressNumber;
                ((AbstractFrierenCard) card).currentLevelRequiredNumber = this.currentLevelRequiredNumber;
            }
        }
        return super.makeStatEquivalentCopy();
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if(this.isMana){
            return true;
        }
        if((this.isChantCard && !p.hasPower(CHANT_WITHOUT_MANA) && !this.isLegendaryMagic)){
            return canChantCardUse(m);
        } else if(this.isLegendaryMagic && !this.isChantCard){
            return canLegendMagicCardUse(m);
        } else if ((this.isChantCard && !p.hasPower(CHANT_WITHOUT_MANA) ) && this.isLegendaryMagic) {
            return canLegendMagicCardUse(m) && canChantCardUse(m);
        }else {
            return super.canUse(p,m);
        }
    }
    public boolean canUseOriginally(AbstractPlayer p, AbstractMonster m){
        return super.canUse(p,m);
    }
    private boolean canChantCardUse(AbstractMonster m){
        return ChantHelper.canChantUse(this,m,this.chantX);
    }
    private boolean canLegendMagicCardUse(AbstractMonster m){
        return LegendMagicHelper.canLegendMagicUse(this,m);
    }
    public void afterChant(){}
    public void taskProgressIncrease(){
        this.flash(FLASH_COLOR);
        currentInLevelProgressNumber++;
        this.updateDescriptionAndCardImg();
    }
    public void updateDescriptionAndCardImg(){}
    public void continueToNextLevel(int currentLevelRequiredNumber){
        this.superFlash();
        this.currentLevel--;
        this.currentInLevelProgressNumber = 0;
        this.currentLevelRequiredNumber = currentLevelRequiredNumber;
        this.updateDescriptionAndCardImg();
    }
    public void initTask(){}
    public void endTask(){
        this.superFlash();
        this.initTask();
        this.initializeDescription();
        this.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
    }
    public void renderCardTip(SpriteBatch sb) {
        super.renderCardTip(sb);
        if (!this.hb.hovered || this.isLocked || (AbstractDungeon.player != null && (AbstractDungeon.player.isDraggingCard || AbstractDungeon.player.inSingleTargetMode)))
            return;
        float drawScale = 0.5F;
        float yPosition1 = Settings.HEIGHT * 0.2F;
        float xPosition1 = Settings.WIDTH * 0.10F;
        float xPosition2 = Settings.WIDTH * 0.25F;
        float xPosition3 = Settings.WIDTH * 0.40F;
        float xPosition4 = Settings.WIDTH * 0.55F;
        float xPosition5 = Settings.WIDTH * 0.70F;
        if (this.prev1 != null) {
            this.prev1.drawScale = drawScale;
            this.prev1.current_x = xPosition1;
            this.prev1.current_y = yPosition1;
            this.prev1.render(sb);
        }
        if (this.prev2 != null) {
            this.prev2.drawScale = drawScale;
            this.prev2.current_x = xPosition2;
            this.prev2.current_y = yPosition1;
            this.prev2.render(sb);
        }
        if (this.prev3 != null) {
            this.prev3.drawScale = drawScale;
            this.prev3.current_x = xPosition3;
            this.prev3.current_y = yPosition1;
            this.prev3.render(sb);
        }
        if (this.prev4 != null) {
            this.prev4.drawScale = drawScale;
            this.prev4.current_x = xPosition4;
            this.prev4.current_y = yPosition1;
            this.prev4.render(sb);
        }
        if (this.prev5 != null) {
            this.prev5.drawScale = drawScale;
            this.prev5.current_x = xPosition5;
            this.prev5.current_y = yPosition1;
            this.prev5.render(sb);
        }
    }
}
