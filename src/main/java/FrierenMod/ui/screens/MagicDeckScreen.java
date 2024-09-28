package FrierenMod.ui.screens;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.effects.ExhaustMagicItemEffect;
import FrierenMod.effects.MagicPropUsingEffect;
import FrierenMod.patches.fields.MagicDeckField;
import FrierenMod.ui.slot.Slot;
import FrierenMod.utils.ModInformation;
import FrierenMod.utils.PublicRes;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomScreen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.GameCursor;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBarListener;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.ArrayList;
import java.util.Comparator;

public class MagicDeckScreen extends CustomScreen implements ScrollBarListener {
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ModInformation.makeID(MagicDeckScreen.class.getSimpleName())).TEXT;
    private AbstractCard hoveredCard = null;
    private AbstractCard clickStartedCard = null;
    private static float deckDrawStartX;
    private static float loadedFactorsDrawStartX;
    private static final float deckDrawStartY = Settings.HEIGHT * 0.28F;
    private static final float loadedFactorsDrawStartY = Settings.HEIGHT * 0.76F;
    private static float deckPadX;
    private static float loadedFactorsPadX;

    private static float deckPadY;
    private float grabStartY = 0.0F, currentDiffY = 0.0F;
    private final float scrollLowerBound = -Settings.DEFAULT_SCROLL_LIMIT;

    private float scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT;
    private float tmpHeaderPosition = Float.NEGATIVE_INFINITY;
    private static final float SCROLL_BAR_THRESHOLD = 500.0F * Settings.scale;
    private final ScrollBar scrollBar;
    private boolean grabbedScreen = false;
    private int prevDeckSize = 0;
    private Comparator<AbstractCard> sortOrder = null;

    private ArrayList<AbstractCard> tmpSortedDeck = null;
    private boolean justSorted = false;
    private int headerScrollLockRemainingFrames = 0;
    private final MagicDeckSortHeader sortHeader;

    private AbstractCard isUsingProp = null;
    private final ArrayList<AbstractCard> chosenCards;
    private boolean previousHasBlackScreen = false;
    private boolean choosable = true;

    private final Slot slot1;
    private final Slot slot2;
    private final Slot slot3;


    public MagicDeckScreen() {
        deckDrawStartX = Settings.WIDTH;
        deckDrawStartX -= 5.0F * AbstractCard.IMG_WIDTH * 0.75F;
        deckDrawStartX -= 4.0F * Settings.CARD_VIEW_PAD_X;
        deckDrawStartX /= 2.0F;
        deckDrawStartX += AbstractCard.IMG_WIDTH * 0.75F / 2.0F;
        deckPadX = AbstractCard.IMG_WIDTH * 0.75F + Settings.CARD_VIEW_PAD_X;
        deckPadY = AbstractCard.IMG_HEIGHT * 0.75F + Settings.CARD_VIEW_PAD_Y;
        loadedFactorsPadX = 1.5F * deckPadX;
        loadedFactorsDrawStartX = deckDrawStartX + 0.5F * deckPadX;
        this.scrollBar = new ScrollBar(this);
        this.scrollBar.move(0.0F, -30.0F * Settings.scale);
        this.sortHeader = new MagicDeckSortHeader(this);
        chosenCards = new ArrayList<>();
        slot1 = new Slot(PublicRes.SLOT_1, 0);
        slot2 = new Slot(PublicRes.SLOT_2, 1);
        slot3 = new Slot(PublicRes.SLOT_3, 2);
    }

    @Override
    public void scrolledUsingBar(float newPercent) {
        this.currentDiffY = MathHelper.valueFromPercentBetween(this.scrollLowerBound, this.scrollUpperBound, newPercent);
        this.updateBarPosition();
    }

    private void updateBarPosition() {
        float percent = MathHelper.percentFromValueBetween(this.scrollLowerBound, this.scrollUpperBound, this.currentDiffY);
        this.scrollBar.parentScrolledToPercent(percent);
    }

    public static class Enum {
        @SpireEnum
        public static AbstractDungeon.CurrentScreen MAGIC_DECK_SCREEN;
    }

    @Override
    public AbstractDungeon.CurrentScreen curScreen() {
        return Enum.MAGIC_DECK_SCREEN;
    }

    public void open() {
        if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.NONE) {
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
            if ((float) ReflectionHacks.getPrivate(AbstractDungeon.overlayMenu, OverlayMenu.class, "blackScreenTarget") == 0.85F) {
                this.previousHasBlackScreen = true;
            }
        }
        reopen();
    }

    @Override
    public void reopen() {
        AbstractDungeon.screen = curScreen();
        AbstractDungeon.isScreenUp = true;
        AbstractDungeon.player.releaseCard();
        CardCrawlGame.sound.play("DECK_OPEN");
        this.currentDiffY = this.scrollLowerBound;
        this.grabStartY = this.scrollLowerBound;
        this.grabbedScreen = false;
        hideCards();
        AbstractDungeon.dynamicBanner.hide();
        AbstractDungeon.isScreenUp = true;
        AbstractDungeon.screen = Enum.MAGIC_DECK_SCREEN;
        AbstractDungeon.overlayMenu.proceedButton.hide();
        AbstractDungeon.overlayMenu.hideCombatPanels();
        AbstractDungeon.overlayMenu.showBlackScreen();
        AbstractDungeon.overlayMenu.cancelButton.show(TEXT[0]);
        calculateScrollBounds();
        cancelUsingProp();
    }

    @Override
    public void close() {
        if (!previousHasBlackScreen)
            AbstractDungeon.overlayMenu.hideBlackScreen();
        AbstractDungeon.isScreenUp = false;
        AbstractDungeon.overlayMenu.cancelButton.hide();
        genericScreenOverlayReset();
        cancelUsingProp();
        AbstractCard[] loadedFactors = getLoadedFactors();
        for (AbstractCard loadedFactor : loadedFactors) {
            if (loadedFactor == null)
                break;
            loadedFactor.drawScale = 0.12F;
            loadedFactor.targetDrawScale = 0.12F;
            loadedFactor.unhover();
        }
        for (AbstractCard c : getDeckWithoutLoadedFactors()) {
            c.drawScale = 0.12F;
            c.targetDrawScale = 0.12F;
            c.unhover();
        }
    }

    @Override
    public void update() {
        boolean isDraggingScrollBar = false;
        if (shouldShowScrollBar())
            isDraggingScrollBar = this.scrollBar.update();
        if (!isDraggingScrollBar)
            updateScrolling();
        updateSlot();
        updatePositions();
        this.sortHeader.update();
        updateClicking();
        updatePropUse();
    }

    @Override
    public void render(SpriteBatch sb) {
        renderSlot(sb);
        if (isUsingProp != null) {
            boolean showVFX = true;
            for (AbstractGameEffect effect : AbstractDungeon.topLevelEffects) {
                if (effect instanceof MagicPropUsingEffect) {
                    showVFX = false;
                    break;
                }
            }
            if (showVFX)
                AbstractDungeon.topLevelEffects.add(new MagicPropUsingEffect(Color.PURPLE, false));
            FontHelper.renderDeckViewTip(sb, String.format(TEXT[1], isUsingProp.name), 146.0F * Settings.scale, Color.PURPLE);
            if (!chosenCards.isEmpty()) {
                StringBuilder isUsingItemsNameBuilder = new StringBuilder();
                for (AbstractCard card : chosenCards) {
                    if (card instanceof AbstractMagicItem) {
                        isUsingItemsNameBuilder.append(card.name).append(",");
                    }
                }
                String isUsingItemsName = isUsingItemsNameBuilder.substring(0, isUsingItemsNameBuilder.length() - 1);
                FontHelper.renderDeckViewTip(sb, String.format(TEXT[2], isUsingItemsName), 96.0F * Settings.scale, Color.WHITE);
            }
        }

        if (shouldShowScrollBar())
            this.scrollBar.render(sb);
        if (this.hoveredCard == null) {
            this.renderMagicDeck(sb);
        } else {
            this.renderMagicDeckExceptOneCard(sb, this.hoveredCard);
            this.hoveredCard.renderHoverShadow(sb);
            this.hoveredCard.render(sb);
        }
        this.sortHeader.render(sb);
        MagicDeckField.getDeck().renderTip(sb);
    }

    public void renderSlot(SpriteBatch sb) {
        slot1.render(sb);
        slot2.render(sb);
        slot3.render(sb);
    }

    public void updateSlot() {
        slot1.target_x = loadedFactorsDrawStartX;
        slot1.target_y = loadedFactorsDrawStartY + this.currentDiffY;
        slot1.update();
        slot2.target_x = loadedFactorsDrawStartX + loadedFactorsPadX;
        slot2.target_y = loadedFactorsDrawStartY + this.currentDiffY;
        slot2.update();
        slot3.target_x = loadedFactorsDrawStartX + 2 * loadedFactorsPadX;
        slot3.target_y = loadedFactorsDrawStartY + this.currentDiffY;
        slot3.update();
    }

    @Override
    public void openingSettings() {
    }

    private void updatePositions() {
        this.hoveredCard = null;
        int lineNum = 0;
        ArrayList<AbstractCard> cards = getDeckWithoutLoadedFactors();
        AbstractCard[] factors = getLoadedFactors();
        if (this.sortOrder != null) {
            cards = new ArrayList<>(cards);
            cards.sort(this.sortOrder);
            this.tmpSortedDeck = cards;
        } else {
            this.tmpSortedDeck = null;
        }
        if (this.justSorted && this.headerScrollLockRemainingFrames <= 0) {
            AbstractCard abstractCard = highestYPosition(cards);
            if (abstractCard != null)
                this.tmpHeaderPosition = abstractCard.current_y;
        }
        for (int i = 0; i < factors.length; i++) {
            if (factors[i] == null)
                break;
            factors[i].target_x = loadedFactorsDrawStartX + i * loadedFactorsPadX;
            factors[i].target_y = loadedFactorsDrawStartY + this.currentDiffY;
            factors[i].update();
            factors[i].updateHoverLogic();
            if (factors[i].hb.hovered)
                this.hoveredCard = factors[i];
        }
        for (int i = 0; i < cards.size(); i++) {
            int mod = i % 5;
            if (mod == 0 && i != 0)
                lineNum++;
            cards.get(i).target_x = deckDrawStartX + mod * deckPadX;
            cards.get(i).target_y = deckDrawStartY + this.currentDiffY - lineNum * deckPadY;
            cards.get(i).update();
            cards.get(i).updateHoverLogic();
            if (cards.get(i).hb.hovered)
                this.hoveredCard = cards.get(i);
        }
        AbstractCard c = highestYPosition(cards);
        if (this.justSorted && c != null) {
            int lerps = 0;
            float lerpY = c.current_y, lerpTarget = c.target_y;
            while (lerpY != lerpTarget) {
                lerpY = MathHelper.cardLerpSnap(lerpY, lerpTarget);
                lerps++;
            }
            this.headerScrollLockRemainingFrames = lerps;
        }
        this.headerScrollLockRemainingFrames -= Settings.FAST_MODE ? 2 : 1;
        if (!cards.isEmpty() && this.sortHeader != null && c != null) {
            this.sortHeader.updateScrollPosition((this.headerScrollLockRemainingFrames <= 0) ? c.current_y : this.tmpHeaderPosition);
            this.justSorted = false;
        }
    }

    public void renderMagicDeck(SpriteBatch sb) {
        for (AbstractCard c : getDeckWithoutLoadedFactors()) {
            c.render(sb);
        }
        for (AbstractCard c : getLoadedFactors()) {
            if (c == null)
                break;
            c.render(sb);
        }
    }

    private ArrayList<AbstractCard> getDeckWithoutLoadedFactors() {
        ArrayList<AbstractCard> retVal = new ArrayList<>();
        for (AbstractCard c : MagicDeckField.getDeck().group) {
            if (c instanceof AbstractMagicItem && ((AbstractMagicItem) c).currentSlot == -1) {
                ((AbstractMagicItem) c).setDescriptionByShowPlaceType(AbstractMagicItem.ShowPlaceType.DECK);
                retVal.add(c);
            }
        }
        return retVal;
    }

    private AbstractCard[] getLoadedFactors() {
        AbstractCard[] retVal = new AbstractCard[3];
        for (AbstractCard c : MagicDeckField.getDeck().group) {
            if (c instanceof AbstractMagicItem && ((AbstractMagicItem) c).currentSlot != -1) {
                if (((AbstractMagicItem) c).currentSlot == 0) {
                    ((AbstractMagicItem) c).setDescriptionByShowPlaceType(AbstractMagicItem.ShowPlaceType.DECK);
                    retVal[0] = c;
                } else if (((AbstractMagicItem) c).currentSlot == 1) {
                    ((AbstractMagicItem) c).setDescriptionByShowPlaceType(AbstractMagicItem.ShowPlaceType.DECK);
                    retVal[1] = c;
                } else if (((AbstractMagicItem) c).currentSlot == 2) {
                    ((AbstractMagicItem) c).setDescriptionByShowPlaceType(AbstractMagicItem.ShowPlaceType.DECK);
                    retVal[2] = c;
                }
            }
        }
        return retVal;
    }

    public void renderMagicDeckExceptOneCard(SpriteBatch sb, AbstractCard card) {
        for (AbstractCard c : getDeckWithoutLoadedFactors()) {
            if (!c.equals(card)) {
                c.render(sb);
            }
        }
        for (AbstractCard c : getLoadedFactors()) {
            if (c == null)
                break;
            if (!c.equals(card)) {
                c.render(sb);
            }
        }
    }

    private boolean shouldShowScrollBar() {
        return (this.scrollUpperBound > SCROLL_BAR_THRESHOLD);
    }

    private void updateScrolling() {
        int y = InputHelper.mY;
        if (!this.grabbedScreen) {
            if (InputHelper.scrolledDown) {
                this.currentDiffY += Settings.SCROLL_SPEED;
            } else if (InputHelper.scrolledUp) {
                this.currentDiffY -= Settings.SCROLL_SPEED;
            }
            if (InputHelper.justClickedLeft) {
                this.grabbedScreen = true;
                this.grabStartY = y - this.currentDiffY;
            }
        } else if (InputHelper.isMouseDown) {
            this.currentDiffY = y - this.grabStartY;
        } else {
            this.grabbedScreen = false;
        }
        if (this.prevDeckSize != getDeckWithoutLoadedFactors().size())
            calculateScrollBounds();
        resetScrolling();
        updateBarPosition();
    }

    private void updateClicking() {
        if (choosable)
            if (this.hoveredCard != null) {
                CardCrawlGame.cursor.changeType(GameCursor.CursorType.INSPECT);
                if (InputHelper.justClickedRight) {
                    if (hoveredCard instanceof AbstractMagicItem && ((AbstractMagicItem) hoveredCard).magicItemRarity == AbstractMagicItem.MagicItemRarity.PROP) {
                        if (this.isUsingProp == null) {
                            this.isUsingProp = hoveredCard;
                            this.isUsingProp.glowColor = Color.GREEN;
                            this.isUsingProp.beginGlowing();
                        } else if (this.isUsingProp == hoveredCard) {
                            cancelUsingProp();
                        }
                    }
                }
                if (InputHelper.justClickedLeft) {
                    addCardToChosenCards(hoveredCard);
                }

                if (InputHelper.justReleasedClickLeft && this.hoveredCard == this.clickStartedCard) {
                    InputHelper.justReleasedClickLeft = false;
                    this.clickStartedCard = null;
                }
            } else {
                this.clickStartedCard = null;
            }
        for (AbstractGameEffect effect : AbstractDungeon.topLevelEffects) {
            if (effect instanceof ExhaustMagicItemEffect) {
                choosable = false;
                return;
            }
        }
        choosable = true;
    }

    private void hideCards() {
        int lineNum = 0;
        ArrayList<AbstractCard> cards = getDeckWithoutLoadedFactors();
        for (int i = 0; i < cards.size(); i++) {
            int mod = i % 5;
            if (mod == 0 && i != 0)
                lineNum++;
            cards.get(i).current_x = deckDrawStartX + mod * deckPadX;
            cards.get(i).current_y = deckDrawStartY + this.currentDiffY - lineNum * deckPadY - MathUtils.random(100.0F * Settings.scale, 200.0F * Settings.scale);
            cards.get(i).targetDrawScale = 0.75F;
            cards.get(i).drawScale = 0.75F;
            cards.get(i).setAngle(0.0F, true);
        }
        AbstractCard[] factors = getLoadedFactors();
        for (int i = 0; i < factors.length; i++) {
            if (factors[i] == null) {
                break;
            }
            factors[i].current_x = loadedFactorsDrawStartX + i * loadedFactorsPadX;
            factors[i].current_y = loadedFactorsDrawStartY + this.currentDiffY - MathUtils.random(100.0F * Settings.scale, 200.0F * Settings.scale);
            factors[i].targetDrawScale = 0.75F;
            factors[i].drawScale = 0.75F;
            factors[i].setAngle(0.0F, true);
        }
    }

    private void calculateScrollBounds() {
        if (getDeckWithoutLoadedFactors().size() > 10) {
            int scrollTmp = getDeckWithoutLoadedFactors().size() / 5 - 2;
            if (getDeckWithoutLoadedFactors().size() % 5 != 0)
                scrollTmp++;
            this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT + scrollTmp * deckPadY;
        } else {
            this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT;
        }
        this.prevDeckSize = getDeckWithoutLoadedFactors().size();
    }

    private void resetScrolling() {
        if (this.currentDiffY < this.scrollLowerBound) {
            this.currentDiffY = MathHelper.scrollSnapLerpSpeed(this.currentDiffY, this.scrollLowerBound);
        } else if (this.currentDiffY > this.scrollUpperBound) {
            this.currentDiffY = MathHelper.scrollSnapLerpSpeed(this.currentDiffY, this.scrollUpperBound);
        }
    }

    public void setSortOrder(Comparator<AbstractCard> sortOrder) {
        if (this.sortOrder != sortOrder)
            this.justSorted = true;
        this.sortOrder = sortOrder;
    }

    private AbstractCard highestYPosition(ArrayList<AbstractCard> cards) {
        if (cards == null)
            return null;
        float highestY = 0.0F;
        AbstractCard retVal = null;
        for (AbstractCard card : cards) {
            if (card.current_y > highestY) {
                highestY = card.current_y;
                retVal = card;
            }
        }
        return retVal;
    }

    private void updatePropUse() {
        if (this.isUsingProp != null && isUsingProp instanceof AbstractMagicItem && chosenCards.size() >= ((AbstractMagicItem) isUsingProp).propCanChooseMaxAmt) {
            if (((AbstractMagicItem) this.isUsingProp).propTakeEffect(chosenCards)) {
                removeProp(isUsingProp);
            }
            cancelUsingProp();
        }
    }

    private void cancelUsingProp() {
        if (this.isUsingProp != null) {
            this.isUsingProp.glowColor = ((Color) ReflectionHacks.getPrivateStatic(AbstractCard.class, "BLUE_BORDER_GLOW_COLOR")).cpy();
            this.isUsingProp.stopGlowing();
            this.isUsingProp = null;
        }
        if (!chosenCards.isEmpty()) {
            for (AbstractCard card : chosenCards) {
                card.stopGlowing();
            }
            chosenCards.clear();
        }
        AbstractDungeon.topLevelEffects.removeIf(effect -> effect instanceof MagicPropUsingEffect);
    }

    private static void removeProp(AbstractCard card) {
        if (MagicDeckField.getDeck().contains(card) && card instanceof AbstractMagicItem && ((AbstractMagicItem) card).magicItemRarity == AbstractMagicItem.MagicItemRarity.PROP) {
            AbstractDungeon.topLevelEffects.add(new ExhaustMagicItemEffect(card));
        }
    }

    private void addCardToChosenCards(AbstractCard card) {
        if (isUsingProp != null && isUsingProp instanceof AbstractMagicItem && chosenCards.size() < ((AbstractMagicItem) isUsingProp).propCanChooseMaxAmt && !this.chosenCards.contains(card) && card instanceof AbstractMagicItem && card != isUsingProp) {
            card.beginGlowing();
            this.chosenCards.add(card);
        }
    }

    @Override
    public boolean allowOpenDeck() {
        return true;
    }

    @Override
    public boolean allowOpenMap() {
        return true;
    }

    private static TextureAtlas.AtlasRegion getImg(Texture texture) {
        return new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
    }
}
