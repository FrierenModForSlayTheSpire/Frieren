package FrierenMod.cards.optionCards.magicFactors;

import FrierenMod.actions.AfterChantFinishedAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.gameHelpers.ActionHelper;
import FrierenMod.powers.AbstractBasePower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.FrierenRes;
import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class AbstractMagicFactor extends AbstractBaseCard {
    public int currentSlot; //-1表示未装载，0表示抽，1手，2弃
    public static final String[] LOAD_MESSAGES = CardCrawlGame.languagePack.getUIString(ModInformation.makeID("MagicFactorLoadMessages")).TEXT;
    public ArrayList<AbstractGameAction> extraActions;
    public FactorRarityType factorRarity;
    public int manaNeedMultipleCoefficient;
    public int manaNeedAddCoefficient;
    public int rewardMultipleCoefficient;
    public int rewardAddCoefficient;
    public AbstractPlayer p = AbstractDungeon.player;

    public AbstractMagicFactor(String ID) {
        super(new CardInfo(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.SKILL, CardTarget.NONE));
        this.currentSlot = -1;
        this.manaNeedMultipleCoefficient = 1;
        this.manaNeedAddCoefficient = 0;
        this.rewardMultipleCoefficient = 1;
        this.rewardAddCoefficient = 0;
    }

    public abstract void takeEffect();

    public String getCombatDescription(String id) {
        return CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION;
    }

    public String getDeckDescription(String id) {
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
            case BAG:
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
        ActionHelper.addToBotAbstract(() -> {
            showVFX();
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

    public void loadMagicFactor(AbstractGameAction[] nextAction, int manaNeed, int chantX) {
        int reward = chantX * this.rewardMultipleCoefficient + this.rewardAddCoefficient;
        this.extraActions = new ArrayList<>();
        if (nextAction != null && nextAction.length > 0)
            this.extraActions.addAll(Arrays.asList(nextAction));
        this.magicNumber = this.baseMagicNumber = manaNeed;
        this.secondMagicNumber = this.baseSecondMagicNumber = reward;
        this.setDescriptionByShowPlaceType(ShowPlaceType.COMBAT);
    }

    public void loadMagicFactor(int manaNeed, int chantX) {
        int reward = chantX * this.rewardMultipleCoefficient + this.rewardAddCoefficient;
        this.extraActions = new ArrayList<>();
        this.magicNumber = this.baseMagicNumber = manaNeed;
        this.secondMagicNumber = this.baseSecondMagicNumber = reward;
        this.setDescriptionByShowPlaceType(ShowPlaceType.COMBAT);
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
        switch (this.factorRarity) {
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

    public enum ShowPlaceType {
        COMBAT,
        BAG
    }

    public enum FactorRarityType {
        BASIC,
        COMMON,
        UNCOMMON,
        RARE,
    }
}
