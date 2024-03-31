package FrierenMod.cards;

import FrierenMod.cards.canAutoAdd.tempCards.CustomLegendaryMagic;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.ChantHelper;
import FrierenMod.gameHelpers.LegendarySpellHelper;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static FrierenMod.gameHelpers.HardCodedPowerHelper.CHANT_WITHOUT_MANA;
import static FrierenMod.utils.PublicRes.*;

public abstract class AbstractMagicianCard extends CustomCard {
    public boolean isChantCard;
    public boolean isMana;
    public boolean isLimitedOverMana;
    public boolean isAccelMana;
    public boolean isLegendarySpell;
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
    public float rotationTimer;
    public int previewIndex;
    public boolean isFrierenFernCard;
    public static final Color FLASH_COLOR = new Color(123.0F/255.0F,236.0F/255.0F,232.0F/255.0F,1.0F);
    public AbstractMagicianCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        initCards();
    }
    public AbstractMagicianCard(String id, String rawDescription, CardType type, CardTarget target) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, ModInformation.makeCardImgPath(id.split(":")[1]), -2, rawDescription, type, CardColor.COLORLESS, CardRarity.SPECIAL, target);
        initCards();
    }
    public AbstractMagicianCard(String id, String img, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, img, cost, CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, type, color, rarity, target);
        initCards();
    }
    public AbstractMagicianCard(String id, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, ModInformation.makeCardImgPath(id.split(":")[1]), cost, CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, type, color, rarity, target);
        initCards();
    }
    public AbstractMagicianCard(String id, int cost, CardType type, CardColor color, CardRarity rarity) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, ModInformation.makeCardImgPath(id.split(":")[1]), cost, CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, type, CardEnums.FRIEREN_CARD, rarity, CardTarget.NONE);
        initCards();
    }
    public AbstractMagicianCard(String id, int cost, CardColor color, CardRarity rarity) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, ModInformation.makeCardImgPath(id.split(":")[1]), cost, CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, CardType.SKILL, CardEnums.FRIEREN_CARD, rarity, CardTarget.NONE);
        initCards();
    }
    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }
    private void initCards(){
        this.initBaseSwitches();
        this.initSpecifiedAttributes();
        this.loadSpecifiedCardBg();
    }
    private void initBaseSwitches(){
        this.isCostModified = false;
        this.isCostModifiedForTurn = false;
        this.isDamageModified = false;
        this.isBlockModified = false;
        this.isMagicNumberModified = false;
        this.isChantCard = false;
        this.isLegendarySpell = false;
        this.isChantXModified = false;
        this.isMana = false;
        this.isLimitedOverMana = false;
        this.isAccelMana = false;
        this.isSecondMagicNumberModified = false;
        this.upgradedSecondMagicNumber = false;
        this.isCostResetCard = false;
        this.isSealCard = false;
        this.isTaskCard = false;
        this.isFrierenFernCard = false;
    }

    public void initSpecifiedAttributes() {

    }

    public void loadSpecifiedCardBg(){
        if(this.isFrierenFernCard){
            String img512, img1024;
            switch(this.type){
                case ATTACK:
                    img512 = BG_ATTACK_FRIEREN_FERN_512;
                    img1024 = BG_ATTACK_FRIEREN_FERN_1024;
                    break;
                case SKILL:
                    img512 = BG_SKILL_FRIEREN_FERN_512;
                    img1024 = BG_SKILL_FRIEREN_FERN_1024;
                    break;
                case POWER:
                    img512 = BG_POWER_FRIEREN_FERN_512;
                    img1024 = BG_POWER_FRIEREN_FERN_1024;
                    break;
                default:
                    img512 = BG_SKILL_FRIEREN_FERN_512;
                    img1024 = BG_SKILL_FRIEREN_FERN_1024;
                    System.out.println("?");
                    break;
            }
            this.setBackgroundTexture(img512, img1024);
        }
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
        if(card instanceof AbstractMagicianCard){
            ((AbstractMagicianCard) card).isChantCard = this.isChantCard;
            ((AbstractMagicianCard) card).isMana = this.isMana;
            ((AbstractMagicianCard) card).isLimitedOverMana = this.isLimitedOverMana;
            ((AbstractMagicianCard) card).isAccelMana = this.isAccelMana;
            ((AbstractMagicianCard) card).isLegendarySpell = this.isLegendarySpell;
            ((AbstractMagicianCard) card).isCostResetCard = this.isCostResetCard;
            ((AbstractMagicianCard) card).isSealCard = this.isSealCard;
            ((AbstractMagicianCard) card).baseChantX = this.baseChantX;
            ((AbstractMagicianCard) card).chantX = this.chantX;
            ((AbstractMagicianCard) card).isChantXModified = this.isChantXModified;
            ((AbstractMagicianCard) card).upgradedChantX = this.upgradedChantX;
            ((AbstractMagicianCard) card).secondMagicNumber = this.secondMagicNumber;
            ((AbstractMagicianCard) card).baseSecondMagicNumber = this.baseSecondMagicNumber;
            ((AbstractMagicianCard) card).secondMisc = this.secondMisc;
            ((AbstractMagicianCard) card).isSecondMagicNumberModified = this.isSecondMagicNumberModified;
            ((AbstractMagicianCard) card).upgradedSecondMagicNumber = this.upgradedSecondMagicNumber;
            if(this.isTaskCard){
                ((AbstractMagicianCard) card).updateDescriptionAndCardImg();
                ((AbstractMagicianCard) card).currentLevel = this.currentLevel;
                ((AbstractMagicianCard) card).currentInLevelProgressNumber = this.currentInLevelProgressNumber;
                ((AbstractMagicianCard) card).currentLevelRequiredNumber = this.currentLevelRequiredNumber;
            }
        }
        return super.makeStatEquivalentCopy();
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if(this.isMana){
            return true;
        }
        if((this.isChantCard && !p.hasPower(CHANT_WITHOUT_MANA) && !this.isLegendarySpell)){
            return canChantCardUse(m);
        } else if(this.isLegendarySpell && !this.isChantCard){
            return canLegendMagicCardUse(m);
        } else if ((this.isChantCard && !p.hasPower(CHANT_WITHOUT_MANA) ) && this.isLegendarySpell) {
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
        return LegendarySpellHelper.canLegendarySpellUse(this,m);
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
    public ArrayList<AbstractCard> getCardsToPreview() {
        return null;
    }
    public void update(){
        super.update();
        if (this.getCardsToPreview() != null && this.hb.hovered)
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                AbstractCard c = (this.getCardsToPreview().get(this.previewIndex)).makeCopy();
                if(this.upgraded)
                    c.upgrade();
                this.cardsToPreview = c;
                if (this.previewIndex == this.getCardsToPreview().size() - 1) {
                    this.previewIndex = 0;
                } else {
                    this.previewIndex++;
                }
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
    }
}
