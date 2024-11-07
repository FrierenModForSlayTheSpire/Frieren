package FrierenMod.cards;

import FrierenMod.cards.tempCards.CustomLegendarySpell;
import FrierenMod.cards.tempCards.SpecializedOffensiveMagic;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import FrierenMod.utils.PublicRes;
import basemod.abstracts.CustomCard;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public abstract class AbstractBaseCard extends CustomCard {
    public int baseChantX = -1;
    public int chantX = -1;
    public boolean isChantXModified;
    public boolean upgradedChantX;
    public int secondMagicNumber = -1;
    public int baseSecondMagicNumber = -1;
    public boolean isSecondMagicNumberModified;
    public boolean upgradedSecondMagicNumber;
    public int raidNumber = -1;
    public int baseRaidNumber = -1;
    public boolean upgradedRaidNumber;
    public boolean isRaidNumberModified;
    public boolean isRaidTriggered;
    protected float rotationTimer;
    protected int previewIndex;

    public static final Color FLASH_COLOR = new Color(123.0F / 255.0F, 236.0F / 255.0F, 232.0F / 255.0F, 1.0F);
    public ArrayList<TooltipInfo> tips = new ArrayList<>();
    public static final String[] cantUseTEXT = CardCrawlGame.languagePack.getUIString(ModInformation.makeID("cantUseMessage")).TEXT;
    protected static final String[] typeTEXT = CardCrawlGame.languagePack.getUIString(ModInformation.makeID("CardStyleText")).TEXT;
    protected static final TextureAtlas.AtlasRegion MANA_TEXTURE_IMG = getImg(ImageMaster.loadImage(PublicRes.BG_MANA_512));

    public AbstractBaseCard(CardInfo info) {
        super(info.baseId, info.name, info.img, info.baseCost, info.rawDescription, info.cardType, info.cardColor, info.cardRarity, info.cardTarget);
        this.initializeBaseSwitches();
        this.initializeSpecifiedAttributes();
        this.loadSpecifiedCardStyle();
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    private void initializeBaseSwitches() {
        this.isCostModified = false;
        this.isCostModifiedForTurn = false;
        this.isDamageModified = false;
        this.isBlockModified = false;
        this.isMagicNumberModified = false;
        this.isRaidNumberModified = false;
        this.isChantXModified = false;
        this.isSecondMagicNumberModified = false;
        this.upgradedSecondMagicNumber = false;
        this.isRaidTriggered = false;
    }

    public void initializeSpecifiedAttributes() {
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
        if (this instanceof CustomLegendarySpell || this instanceof SpecializedOffensiveMagic)
            return super.makeStatEquivalentCopy();
        AbstractCard card = this.makeCopy();
        if (card instanceof AbstractBaseCard) {
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
        }
        return super.makeStatEquivalentCopy();
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (this.hasTag(Enum.CHANT) && CombatHelper.cannotChant(chantX)) {
            this.cantUseMessage = cantUseTEXT[0];
            return false;
        }
        if (this.hasTag(Enum.LEGENDARY_SPELL) && CombatHelper.cannotPlayLegendarySpell()) {
            this.cantUseMessage = cantUseTEXT[1];
            return false;
        }
        return super.canUse(p, m);
    }

    @Override
    public void triggerOnGlowCheck() {
        if (this.raidNumber != -1 && this.hasTag(Enum.RAID))
            this.glowColor = (CombatHelper.canRaidTakeEffect(this.raidNumber)) ? AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy() : AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }

    public boolean canUseOriginally(AbstractPlayer p, AbstractMonster m) {
        return super.canUse(p, m);
    }

    public void afterChant() {
    }

    public ArrayList<AbstractCard> getCardsToPreview() {
        return null;
    }

    @Override
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

    public void afterSynchroFinished(AbstractCard card) {
    }

    public static class Enum {
        @SpireEnum
        public static AbstractCard.CardTags SYNCHRO;
        @SpireEnum
        public static AbstractCard.CardTags ACCEL_SYNCHRO;
        @SpireEnum
        public static AbstractCard.CardTags LIMIT_OVER_SYNCHRO;
        @SpireEnum
        public static AbstractCard.CardTags CHANT;
        @SpireEnum
        public static AbstractCard.CardTags MANA;
        @SpireEnum
        public static AbstractCard.CardTags LEGENDARY_SPELL;
        @SpireEnum
        public static AbstractCard.CardTags SEAL;
        @SpireEnum
        public static AbstractCard.CardTags COST_REST;
        @SpireEnum
        public static AbstractCard.CardTags CAN_NOT_RANDOM_GENERATED_IN_COMBAT;
        @SpireEnum
        public static AbstractCard.CardTags LESS_CHANCE_TO_MEET;
        @SpireEnum
        public static AbstractCard.CardTags NEVER_DROP;
        @SpireEnum
        public static AbstractCard.CardTags RAID;
        @SpireEnum
        public static AbstractCard.CardTags SPEED;
        @SpireEnum
        public static AbstractCard.CardTags FUSION;
    }

    protected static TextureAtlas.AtlasRegion getImg(Texture texture) {
        return new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
    }

    protected static TextureAtlas.AtlasRegion getImg(Texture texture, int width, int height) {
        return new TextureAtlas.AtlasRegion(texture, 0, 0, width, height);
    }

    protected void renderHelper(SpriteBatch sb, Color color, TextureAtlas.AtlasRegion img, float drawX, float drawY) {
        sb.setColor(color);
        sb.draw(img, drawX + img.offsetX - (float) img.originalWidth / 2.0F, drawY + img.offsetY - (float) img.originalHeight / 2.0F, (float) img.originalWidth / 2.0F - img.offsetX, (float) img.originalHeight / 2.0F - img.offsetY, (float) img.packedWidth, (float) img.packedHeight, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle);
    }

}
