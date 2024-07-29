package FrierenMod.cards;

import FrierenMod.cards.tempCards.CustomLegendarySpell;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.CustomCard;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public abstract class AbstractBaseCard extends CustomCard {
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
    @Deprecated
    public int secondMisc = 0;
    public boolean isSecondMagicNumberModified;
    public boolean upgradedSecondMagicNumber;
    public int raidNumber = -1;
    public int baseRaidNumber = -1;
    public boolean upgradedRaidNumber;
    public boolean isRaidNumberModified;
    public int currentLevel = -1;
    public int currentLevelRequiredNumber = -1;
    public int currentInLevelProgressNumber = -1;
    public float rotationTimer;
    public int previewIndex;

    public static final Color FLASH_COLOR = new Color(123.0F / 255.0F, 236.0F / 255.0F, 232.0F / 255.0F, 1.0F);
    public ArrayList<TooltipInfo> tips = new ArrayList<>();
    public static final String[] cantUseTEXT = CardCrawlGame.languagePack.getUIString(ModInformation.makeID("cantUseMessage")).TEXT;

    public AbstractBaseCard(CardInfo info) {
        super(info.baseId, info.name, info.img, info.baseCost, info.rawDescription, info.cardType, info.cardColor, info.cardRarity, info.cardTarget);
        initCards();
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    private void initCards() {
        this.initBaseSwitches();
        this.initSpecifiedAttributes();
        this.loadSpecifiedCardStyle();
    }

    private void initBaseSwitches() {
        this.isCostModified = false;
        this.isCostModifiedForTurn = false;
        this.isDamageModified = false;
        this.isBlockModified = false;
        this.isMagicNumberModified = false;
        this.isRaidNumberModified = false;
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
    }

    public void initSpecifiedAttributes() {
    }

    public void loadSpecifiedCardStyle() {
    }

    public void upgradeChantX(int amount) {
        this.baseChantX += amount;
        this.chantX = this.baseChantX;
        this.upgradedChantX = true;
    }

    public void upgradeSecondMagicNumber(int amount) {
        this.baseSecondMagicNumber += amount;
        this.secondMagicNumber = this.baseSecondMagicNumber;
        this.upgradedSecondMagicNumber = true;
    }

    public void upgradeRaidNumber(int amount) {
        this.baseRaidNumber += amount;
        this.raidNumber = this.baseRaidNumber;
        this.upgradedRaidNumber = true;
    }

    public void displayUpgrades() {
        super.displayUpgrades();
        if (this.upgradedChantX) {
            this.chantX = this.baseChantX;
            this.isChantXModified = true;
        }
        if (this.upgradedSecondMagicNumber) {
            this.secondMagicNumber = this.baseSecondMagicNumber;
            this.isSecondMagicNumberModified = true;
        }
        if (this.upgradedRaidNumber) {
            this.raidNumber = this.baseRaidNumber;
            this.isRaidNumberModified = true;
        }
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        if (this instanceof CustomLegendarySpell)
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
        if (card instanceof AbstractBaseCard) {
            ((AbstractBaseCard) card).isChantCard = this.isChantCard;
            ((AbstractBaseCard) card).isMana = this.isMana;
            ((AbstractBaseCard) card).isLimitedOverMana = this.isLimitedOverMana;
            ((AbstractBaseCard) card).isAccelMana = this.isAccelMana;
            ((AbstractBaseCard) card).isLegendarySpell = this.isLegendarySpell;
            ((AbstractBaseCard) card).isCostResetCard = this.isCostResetCard;
            ((AbstractBaseCard) card).isSealCard = this.isSealCard;
            ((AbstractBaseCard) card).baseChantX = this.baseChantX;
            ((AbstractBaseCard) card).chantX = this.chantX;
            ((AbstractBaseCard) card).isChantXModified = this.isChantXModified;
            ((AbstractBaseCard) card).upgradedChantX = this.upgradedChantX;
            ((AbstractBaseCard) card).secondMagicNumber = this.secondMagicNumber;
            ((AbstractBaseCard) card).baseSecondMagicNumber = this.baseSecondMagicNumber;
            ((AbstractBaseCard) card).isSecondMagicNumberModified = this.isSecondMagicNumberModified;
            ((AbstractBaseCard) card).upgradedSecondMagicNumber = this.upgradedSecondMagicNumber;
            ((AbstractBaseCard) card).baseRaidNumber = this.baseRaidNumber;
            ((AbstractBaseCard) card).raidNumber = this.raidNumber;
            ((AbstractBaseCard) card).upgradedRaidNumber = this.upgradedRaidNumber;
            ((AbstractBaseCard) card).isRaidNumberModified = this.isRaidNumberModified;
            if (this.isTaskCard) {
                ((AbstractBaseCard) card).currentLevel = this.currentLevel;
                ((AbstractBaseCard) card).currentInLevelProgressNumber = this.currentInLevelProgressNumber;
                ((AbstractBaseCard) card).currentLevelRequiredNumber = this.currentLevelRequiredNumber;
                ((AbstractBaseCard) card).updateDescriptionAndCardImg();
            }
        }
        return super.makeStatEquivalentCopy();
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (this.isChantCard && CombatHelper.cannotChant(chantX)) {
            this.cantUseMessage = cantUseTEXT[0];
            return false;
        }
        if (this.isLegendarySpell && CombatHelper.cannotPlayLegendarySpell()) {
            this.cantUseMessage = cantUseTEXT[1];
            return false;
        }
        return super.canUse(p, m);
    }

    public boolean canUseOriginally(AbstractPlayer p, AbstractMonster m) {
        return super.canUse(p, m);
    }

    public void afterChant() {
    }

    public void taskProgressIncrease() {
        this.flash(FLASH_COLOR);
        currentInLevelProgressNumber++;
        this.updateDescriptionAndCardImg();
    }

    public void updateDescriptionAndCardImg() {
    }

    public void continueToNextLevel(int currentLevelRequiredNumber) {
        this.superFlash();
        this.currentLevel--;
        this.currentInLevelProgressNumber = 0;
        this.currentLevelRequiredNumber = currentLevelRequiredNumber;
        this.updateDescriptionAndCardImg();
    }

    public void initTask() {
    }

    public void endTask() {
        this.superFlash();
        this.initTask();
        this.initializeDescription();
        this.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
    }

    public ArrayList<AbstractCard> getCardsToPreview() {
        return null;
    }

    public void update() {
        super.update();
        if (this.getCardsToPreview() != null && this.hb.hovered)
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                AbstractCard c = (this.getCardsToPreview().get(this.previewIndex)).makeCopy();
                if (this.upgraded)
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

    public void afterChantFinished() {
    }

    public void afterSynchroFinished() {
    }
    public static class Enum{
        @SpireEnum
        public static AbstractCard.CardTags CAN_NOT_RANDOM_GENERATED_IN_COMBAT;
    }
}
