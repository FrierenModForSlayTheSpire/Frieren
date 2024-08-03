package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.actions.AfterChantFinishedAction;
import FrierenMod.actions.ExhaustManaInCardGroupAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.gameHelpers.ActionHelper;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.powers.AbstractBasePower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.FrierenRes;
import FrierenMod.utils.ModInformation;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractMagicItem extends AbstractBaseCard {
    public int currentSlot; //-1表示未装载，0表示抽，1手，2弃
    public static final String[] LOAD_MESSAGES = CardCrawlGame.languagePack.getUIString(ModInformation.makeID("MagicFactorLoadMessages")).TEXT;
    public ArrayList<AbstractGameAction> extraActions;
    public MagicItemRarity magicItemRarity;
    public int manaNeedMultipleCoefficient;
    public int manaNeedAddCoefficient;
    public int rewardMultipleCoefficient;
    public int rewardAddCoefficient;
    public AbstractPlayer p = AbstractDungeon.player;
    public static String[] TEXT = CardCrawlGame.languagePack.getUIString(ModInformation.makeID("MagicItemTip")).TEXT;
    public int propCanChooseMaxAmt;

    public AbstractMagicItem(String ID) {
        super(new CardInfo(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.SKILL, CardTarget.NONE));
        this.currentSlot = -1;
        this.manaNeedMultipleCoefficient = 1;
        this.manaNeedAddCoefficient = 0;
        this.rewardMultipleCoefficient = 1;
        this.rewardAddCoefficient = 0;
        this.propCanChooseMaxAmt = 0;
    }

    public void takeEffect() {
    }

    public boolean propTakeEffect(ArrayList<AbstractCard> chosenCards) {
        return false;
    }

    public String getCombatDescription(String id) {
        return CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION;
    }

    public String getDeckDescription(String id) {
        if (this.magicItemRarity == MagicItemRarity.PROP)
            return CardCrawlGame.languagePack.getCardStrings(id).EXTENDED_DESCRIPTION[0];
        return CardCrawlGame.languagePack.getCardStrings(id).EXTENDED_DESCRIPTION[0] + LOAD_MESSAGES[currentSlot + 1];
    }

    public void setCurrentSlot(int currentSlot) {
        this.currentSlot = currentSlot;
    }

    public void setDescriptionByShowPlaceType(ShowPlaceType type) {
        switch (type) {
            case COMBAT:
                this.rawDescription = getCombatDescription(this.cardID);
                break;
            case DECK:
                this.rawDescription = getDeckDescription(this.cardID);
                break;
            default:
                this.rawDescription = "ERROR!";
                break;
        }
        this.initializeDescription();
    }

    @Override
    public void onChoseThisOption() {
        if (this.magicItemRarity != MagicItemRarity.PROP)
            ActionHelper.addToBotAbstract(() -> {
                showVFX();
                exhaustMana();
                takeEffect();
                triggerPowers();
                triggerCards();
                if (extraActions != null && !extraActions.isEmpty()) {
                    for (AbstractGameAction action : extraActions) {
                        this.addToBot(action);
                    }
                }
            });
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    public void loadMagicFactor(int chantX, AbstractGameAction[] nextAction) {
        loadMagicFactor(chantX);
        if (nextAction != null && nextAction.length > 0)
            this.extraActions.addAll(Arrays.asList(nextAction));
    }

    public void loadMagicFactor(int chantX) {
        int reward = chantX * this.rewardMultipleCoefficient + this.rewardAddCoefficient;
        this.magicNumber = this.baseMagicNumber = CombatHelper.getManaNeed(chantX, this);
        this.block = this.baseBlock = reward;
        this.applyPowers();
        this.secondMagicNumber = this.baseSecondMagicNumber = reward;
        this.setDescriptionByShowPlaceType(ShowPlaceType.COMBAT);
        this.extraActions = new ArrayList<>();
    }

    public void triggerPowers() {
        for (AbstractPower po : AbstractDungeon.player.powers)
            if (po instanceof AbstractBasePower) {
                ((AbstractBasePower) po).afterChant();
                this.addToBot(new AfterChantFinishedAction((AbstractBasePower) po));
            }

    }

    public void triggerCards() {
        AbstractPlayer p = AbstractDungeon.player;
        triggerCardsInCardGroup(p.drawPile);
        triggerCardsInCardGroup(p.hand);
        triggerCardsInCardGroup(p.discardPile);
    }

    protected void triggerCardsInCardGroup(CardGroup group) {
        for (AbstractCard c : group.group)
            if (c instanceof AbstractBaseCard) {
                ((AbstractBaseCard) c).afterChant();
                this.addToBot(new AfterChantFinishedAction((AbstractBaseCard) c));
            }
    }

    public void showVFX() {
        switch (this.magicItemRarity) {
            default:
            case BASIC:
            case COMMON:
                addToBot(new VFXAction(p, new VerticalAuraEffect(FrierenRes.RENDER_COLOR, p.hb.cX, p.hb.cY), 0.1F));
                addToBot(new VFXAction(p, new VerticalAuraEffect(Color.TAN, p.hb.cX, p.hb.cY), 0.05F));
                break;
            case UNCOMMON:
                addToBot(new VFXAction(p, new VerticalAuraEffect(FrierenRes.RENDER_COLOR, p.hb.cX, p.hb.cY), 0.1F));
                addToBot(new VFXAction(p, new VerticalAuraEffect(Color.SKY, p.hb.cX, p.hb.cY), 0.05F));
                break;
            case RARE:
                addToBot(new VFXAction(p, new VerticalAuraEffect(FrierenRes.RENDER_COLOR, p.hb.cX, p.hb.cY), 0.1F));
                addToBot(new VFXAction(p, new VerticalAuraEffect(Color.GOLD, p.hb.cX, p.hb.cY), 0.05F));
                break;
        }
    }

    public void exhaustMana() {
        if (this.magicNumber > 0)
            this.addToBot(new ExhaustManaInCardGroupAction(magicNumber, currentSlot));
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard c = this.makeCopy();
        if (c instanceof AbstractMagicItem) {
            ((AbstractMagicItem) c).currentSlot = this.currentSlot;
        }
        return c;
    }

    public List<TooltipInfo> getCustomTooltips() {
        if (this.magicItemRarity == MagicItemRarity.PROP){
            this.tips.clear();
            this.tips.add(new TooltipInfo(TEXT[2], TEXT[3]));
        }
        else {
            this.tips.clear();
            this.tips.add(new TooltipInfo(TEXT[0], TEXT[1]));
        }
        return this.tips;
    }

    @SpireOverride
    protected void renderMainBorder(SpriteBatch sb){
        if (this.isGlowing) {
            sb.setBlendFunction(770, 1);
            TextureAtlas.AtlasRegion img;
            switch (this.type) {
                case ATTACK:
                    img = ImageMaster.CARD_ATTACK_BG_SILHOUETTE;
                    break;
                case POWER:
                    img = ImageMaster.CARD_POWER_BG_SILHOUETTE;
                    break;
                default:
                    img = ImageMaster.CARD_SKILL_BG_SILHOUETTE;
            }
            sb.setColor(this.glowColor);
            sb.draw(img, this.current_x + img.offsetX - (float)img.originalWidth / 2.0F, this.current_y + img.offsetY - (float)img.originalWidth / 2.0F, (float)img.originalWidth / 2.0F - img.offsetX, (float)img.originalWidth / 2.0F - img.offsetY, (float)img.packedWidth, (float)img.packedHeight, this.drawScale * Settings.scale * 1.04F, this.drawScale * Settings.scale * 1.03F, this.angle);
        }
    }
    public enum ShowPlaceType {
        COMBAT,
        DECK
    }

    public enum MagicItemRarity {
        BASIC,
        COMMON,
        UNCOMMON,
        RARE,
        PROP
    }
}
