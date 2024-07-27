package FrierenMod.ui.screens;

import FrierenMod.cards.optionCards.magicItems.AbstractMagicItem;
import FrierenMod.patches.fields.MagicDeckField;
import basemod.abstracts.CustomScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.GameCursor;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBarListener;

import java.util.ArrayList;
import java.util.Comparator;

import static com.megacrit.cardcrawl.ui.buttons.CancelButton.TEXT;

public class MagicDeckScreen extends CustomScreen implements ScrollBarListener {
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
        AbstractDungeon.overlayMenu.cancelButton.show("Cancel");
        calculateScrollBounds();
    }

    @Override
    public void reopen() {
        if (Settings.isControllerMode) {
            Gdx.input.setCursorPosition(10, Settings.HEIGHT / 2);
        }
        AbstractDungeon.overlayMenu.cancelButton.show(TEXT[0]);
    }
    @Override
    public void close() {
        AbstractDungeon.overlayMenu.hideBlackScreen();
        AbstractDungeon.isScreenUp = false;
        AbstractDungeon.overlayMenu.cancelButton.hide();
        genericScreenOverlayReset();
        for (AbstractCard c : getItemBag()) {
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
    }

    @Override
    public void render(SpriteBatch sb) {
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
    }

    @Override
    public void openingSettings() {
        // Required if you want to reopen your screen when the settings screen closes
        AbstractDungeon.previousScreen = curScreen();
    }

    private void updatePositions() {
        this.hoveredCard = null;
        int lineNum = 0;
        ArrayList<AbstractCard> cards = getItemBag();
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
        for (AbstractCard c : getItemBag()) {
            c.render(sb);
        }
    }

    public ArrayList<AbstractCard> getItemBag() {
        ArrayList<AbstractCard> retVal = MagicDeckField.getDeck().group;
        for (AbstractCard c : retVal) {
            if(c instanceof AbstractMagicItem){
                ((AbstractMagicItem) c).setDescriptionByShowPlaceType(AbstractMagicItem.ShowPlaceType.DECK);
            }
        }
        return retVal;
    }

    public void renderMagicFactorDeckExceptOneCard(SpriteBatch sb, AbstractCard card) {
        for (AbstractCard c : getItemBag()) {
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
        if (this.prevDeckSize != getItemBag().size())
            calculateScrollBounds();
        resetScrolling();
        updateBarPosition();
    }
    private void updateClicking() {
        if (this.hoveredCard != null) {
            CardCrawlGame.cursor.changeType(GameCursor.CursorType.INSPECT);
            if (InputHelper.justClickedLeft)
                this.clickStartedCard = this.hoveredCard;
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
        ArrayList<AbstractCard> cards = getItemBag();
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
        if (getItemBag().size() > 10) {
            int scrollTmp = getItemBag().size() / 5 - 2;
            if (getItemBag().size() % 5 != 0)
                scrollTmp++;
            this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT + scrollTmp * padY;
        } else {
            this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT;
        }
        this.prevDeckSize = getItemBag().size();
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
}
