package FrierenMod.ui.screens;

import FrierenMod.cards.optionCards.magicItems.AbstractMagicItem;
import FrierenMod.effects.ExhaustMagicItemEffect;
import FrierenMod.effects.MagicPropUsingEffect;
import FrierenMod.patches.fields.MagicDeckField;
import FrierenMod.utils.ModInformation;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomScreen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.GameCursor;
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
    private static float drawStartX;
    private static float drawStartY = Settings.HEIGHT * 0.66F;
    private static float padX;

    private static float padY;
    private float grabStartY = 0.0F, currentDiffY = 0.0F;
    private float scrollLowerBound = -Settings.DEFAULT_SCROLL_LIMIT;

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

    public MagicDeckScreen() {
        drawStartX = Settings.WIDTH;
        drawStartX -= 5.0F * AbstractCard.IMG_WIDTH * 0.75F;
        drawStartX -= 4.0F * Settings.CARD_VIEW_PAD_X;
        drawStartX /= 2.0F;
        drawStartX += AbstractCard.IMG_WIDTH * 0.75F / 2.0F;
        padX = AbstractCard.IMG_WIDTH * 0.75F + Settings.CARD_VIEW_PAD_X;
        padY = AbstractCard.IMG_HEIGHT * 0.75F + Settings.CARD_VIEW_PAD_Y;
        this.scrollBar = new ScrollBar(this);
        this.scrollBar.move(0.0F, -30.0F * Settings.scale);
        this.sortHeader = new MagicDeckSortHeader(this);
        chosenCards = new ArrayList<>();
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
        public static AbstractDungeon.CurrentScreen MAGIC_FACTOR_SCREEN;
    }

    @Override
    public AbstractDungeon.CurrentScreen curScreen() {
        return Enum.MAGIC_FACTOR_SCREEN;
    }

    // Note that this can be private and take any parameters you want.
    // When you call openCustomScreen it finds the first method named "open"
    // and calls it with whatever arguments were passed to it.
    private void open() {
        if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.NONE)
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
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
        AbstractDungeon.screen = Enum.MAGIC_FACTOR_SCREEN;
        AbstractDungeon.overlayMenu.proceedButton.hide();
        AbstractDungeon.overlayMenu.hideCombatPanels();
        AbstractDungeon.overlayMenu.showBlackScreen();
        AbstractDungeon.overlayMenu.cancelButton.show(TEXT[0]);
        calculateScrollBounds();
    }

    @Override
    public void reopen() {
        cancelUsingProp();
        AbstractDungeon.overlayMenu.cancelButton.show(TEXT[0]);
    }

    @Override
    public void close() {
        AbstractDungeon.overlayMenu.hideBlackScreen();
        AbstractDungeon.isScreenUp = false;
        AbstractDungeon.overlayMenu.cancelButton.hide();
        genericScreenOverlayReset();
        cancelUsingProp();
        for (AbstractCard c : getDeck()) {
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
        updatePositions();
        this.sortHeader.update();
        updateClicking();
        updatePropUse();
    }

    @Override
    public void render(SpriteBatch sb) {
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
            this.renderMagicFactorDeck(sb);
        } else {
            this.renderMagicFactorDeckExceptOneCard(sb, this.hoveredCard);
            this.hoveredCard.renderHoverShadow(sb);
            this.hoveredCard.render(sb);
        }
        this.sortHeader.render(sb);
        MagicDeckField.getDeck().renderTip(sb);
    }

    @Override
    public void openingSettings() {
        // Required if you want to reopen your screen when the settings screen closes
        AbstractDungeon.previousScreen = curScreen();
    }

    private void updatePositions() {
        this.hoveredCard = null;
        int lineNum = 0;
        ArrayList<AbstractCard> cards = getDeck();
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
        for (int i = 0; i < cards.size(); i++) {
            int mod = i % 5;
            if (mod == 0 && i != 0)
                lineNum++;
            cards.get(i).target_x = drawStartX + mod * padX;
            cards.get(i).target_y = drawStartY + this.currentDiffY - lineNum * padY;
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

    public void renderMagicFactorDeck(SpriteBatch sb) {
        for (AbstractCard c : getDeck()) {
            c.render(sb);
        }
    }

    public ArrayList<AbstractCard> getDeck() {
        ArrayList<AbstractCard> retVal = MagicDeckField.getDeck().group;
        for (AbstractCard c : retVal) {
            if (c instanceof AbstractMagicItem) {
                ((AbstractMagicItem) c).setDescriptionByShowPlaceType(AbstractMagicItem.ShowPlaceType.DECK);
            }
        }
        return retVal;
    }

    public void renderMagicFactorDeckExceptOneCard(SpriteBatch sb, AbstractCard card) {
        for (AbstractCard c : getDeck()) {
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
        if (this.prevDeckSize != getDeck().size())
            calculateScrollBounds();
        resetScrolling();
        updateBarPosition();
    }

    private void updateClicking() {
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
    }

    private void hideCards() {
        int lineNum = 0;
        ArrayList<AbstractCard> cards = getDeck();
        for (int i = 0; i < cards.size(); i++) {
            int mod = i % 5;
            if (mod == 0 && i != 0)
                lineNum++;
            cards.get(i).current_x = drawStartX + mod * padX;
            cards.get(i).current_y = drawStartY + this.currentDiffY - lineNum * padY - MathUtils.random(100.0F * Settings.scale, 200.0F * Settings.scale);
            cards.get(i).targetDrawScale = 0.75F;
            cards.get(i).drawScale = 0.75F;
            cards.get(i).setAngle(0.0F, true);
        }
    }

    private void calculateScrollBounds() {
        if (getDeck().size() > 10) {
            int scrollTmp = getDeck().size() / 5 - 2;
            if (getDeck().size() % 5 != 0)
                scrollTmp++;
            this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT + scrollTmp * padY;
        } else {
            this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT;
        }
        this.prevDeckSize = getDeck().size();
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
}
