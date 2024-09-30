package FrierenMod.ui.screens;

import FrierenMod.gameHelpers.SlotBgHelper;
import FrierenMod.ui.slot.Slot;
import FrierenMod.utils.Log;
import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBarListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SlotBgLibrary implements ScrollBarListener {
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ModInformation.makeID(SlotBgLibrary.class.getSimpleName())).TEXT;
    public String openSlotId;
    public int openSlotIndex;
    private Color screenColor = Color.WHITE.cpy();
    private ArrayList<Slot> slots;
    private ArrayList<Slot> previewSlots;
    private static final float progressBarStartX = 576.0F * Settings.xScale;
    private static final float progressBarStartY = Settings.HEIGHT - 50.0F * Settings.scale;
    private static final float slotStartX = 350.0F * Settings.scale;
    private static final float slotStartY = Settings.HEIGHT - 300.0F * Settings.scale;
    private static final float yesButtonX = 1350.0F * Settings.scale;
    private static final float yesButtonY = 0.0F;
    private static final float noButtonX = Settings.WIDTH / 2.0F - 200.0F * Settings.scale;
    private static final float noButtonY = 0.0F;
    private static final float pad = 400.0F * Settings.scale;
    private final float scrollLowerBound = -Settings.DEFAULT_SCROLL_LIMIT;
    private static final float SCROLL_BAR_THRESHOLD = 500.0F * Settings.scale;

    private float scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT;
    private float grabStartY = 0.0F, currentDiffY = 0.0F;
    private boolean grabbedScreen = false;
    private final ScrollBar scrollBar;
    private int prevLibrarySize = 0;
    private Slot chosenSlot = null;
    public boolean shown = false;
    public MainMenuScreen.CurScreen sourceScreen = null;
    private static Texture background;
    private static Texture board;
    private static Texture yesButtonTexture;
    private static Texture yesButton;
    private static Texture grayYesButton;
    private static Texture noButtonTexture;
    private static Color yesFontColor;
    private static Color noFontColor;
    private static int NUM_PER_ROW = 4;
    public Hitbox yesHb;

    public Hitbox noHb;

    private float progressBarWidth = 768.0F * Settings.xScale;
    protected float progressPercent;
    private int collectedNumber;
    private int allNumber;


    public SlotBgLibrary() {
        this.slots = SlotBgHelper.getAllSlotsFromFiles();
        if (slots != null)
            this.allNumber = slots.size();
        else{
            this.allNumber = 0;
            Log.logger.info("WHY SLOTS IS NULL?");
        }
        this.scrollBar = new ScrollBar(this);
        background = ImageMaster.loadImage(ModInformation.makeUIPath("slotPreviewAndLibrary/SlotLibraryBg"));
        board = ImageMaster.loadImage(ModInformation.makeUIPath("slotPreviewAndLibrary/SlotLibraryBgBoard"));
        yesButton = ImageMaster.loadImage(ModInformation.makeUIPath("slotPreviewAndLibrary/saveButton"));
        grayYesButton = ImageMaster.loadImage(ModInformation.makeUIPath("slotPreviewAndLibrary/graySaveButton"));
        noButtonTexture = ImageMaster.loadImage(ModInformation.makeUIPath("slotPreviewAndLibrary/cancelButton"));
        yesHb = new Hitbox(yesButton.getWidth(), yesButton.getHeight() / 2.5F);
        noHb = new Hitbox(noButtonTexture.getWidth(), noButtonTexture.getHeight());
    }

    public void sortedSlots() {
        List<String> collectedIds = Arrays.asList(SlotBgHelper.progressString.split(","));
        slots.sort((slot1, slot2) -> {
            String id1 = slot1.id;
            String id2 = slot2.id;
            int index1 = collectedIds.indexOf(id1);
            int index2 = collectedIds.indexOf(id2);
            if (index1 != -1 && index2 != -1) {
                return Integer.compare(index1, index2);
            }
            if (index1 != -1) {
                return -1;
            }
            if (index2 != -1) {
                return 1;
            }
            return 0;
        });
    }

    public void refreshLockedSlots() {
        List<String> collectedIds = Arrays.asList(SlotBgHelper.progressString.split(","));
        for (Slot slot : slots) {
            if (!collectedIds.contains(slot.id)) {
                slot.setLockedInLibrary();
            }
        }
    }

    public void refreshChosenSlot() {
        for (Slot slot : slots) {
            if (slot.id.equals(openSlotId)) {
                this.chosenSlot = slot;
            }
        }
    }

    public void show(String openSlotId, int openSlotIndex, ArrayList<Slot> previousSlots) {
        this.shown = true;
        this.openSlotId = openSlotId;
        this.openSlotIndex = openSlotIndex;
        this.previewSlots = previousSlots;
        this.collectedNumber = SlotBgHelper.getCollectedSlotBgNumber();
        if (this.allNumber != 0) {
            this.progressPercent = (float) collectedNumber / this.allNumber;
        } else {
            Log.logger.info("WHY ALL NUMBER IS ZERO?");
            this.progressPercent = 0.0F;
        }
        this.scrollBar.move(0.0F, -30.0F * Settings.scale);
        this.yesHb.x = yesButtonX;
        this.yesHb.y = yesButtonY;
        this.noHb.x = noButtonX;
        this.noHb.y = noButtonY;
        sortedSlots();
        refreshLockedSlots();
        refreshChosenSlot();
    }

    public void save() {
        SlotBgHelper.changeLoading(this.openSlotIndex, this.chosenSlot.id);
        previewSlots.get(openSlotIndex).refreshSlot(chosenSlot.id);
        close();
    }

    public void cancel() {
        close();
    }

    public void close() {
        this.yesHb.move(-1000.0F, -1000.0F);
        this.noHb.move(-1000.0F, -1000.0F);
        this.shown = false;
        if (this.sourceScreen == null) {
            CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.CHAR_SELECT;
        } else {
            CardCrawlGame.mainMenuScreen.screen = this.sourceScreen;
            this.sourceScreen = null;
        }
    }

    public void update() {
        if (this.shown) {
            boolean isDraggingScrollBar = false;
            if (shouldShowScrollBar())
                isDraggingScrollBar = this.scrollBar.update();
            if (!isDraggingScrollBar)
                updateScrolling();
            updatePositions();
            updateYesNoButtons();
            updateSlots();
        }
    }

    public void updateSlots() {
        for (Slot slot : slots) {
            slot.updateHoverLogicInLibrary();
            if (slot.hovered) {
                if (InputHelper.justClickedLeft) {
                    InputHelper.justClickedLeft = false;
                    if (!slot.locked)
                        this.chosenSlot = slot;
                    slot.unhover();
                }
            }
            if (slot.id.equals(chosenSlot.id)) {
                slot.beginGlowing();
            } else {
                slot.stopGlowing();
            }
        }
    }

    public void updateYesNoButtons() {
        updateYes();
        updateNo();
        if (InputActionSet.confirm.isJustPressed()) {
            save();
        } else if (InputHelper.pressedEscape) {
            InputHelper.pressedEscape = false;
            cancel();
        }
    }

    private void updatePositions() {
        int lineNum = 0;
        for (int i = 0; i < slots.size(); i++) {
            int mod = i % NUM_PER_ROW;
            if (mod == 0 && i != 0)
                lineNum++;
            slots.get(i).target_x = slotStartX + mod * pad;
            slots.get(i).target_y = slotStartY + this.currentDiffY - lineNum * pad;
            slots.get(i).update();
        }
    }

    private void updateYes() {
        this.yesHb.update();
        if (this.yesHb.justHovered)
            CardCrawlGame.sound.play("UI_HOVER");
        if (!Objects.equals(chosenSlot.id, openSlotId)) {
            yesButtonTexture = yesButton;
            if (yesHb.hovered) {
                yesFontColor = Color.GOLD;
                if (InputHelper.justClickedLeft) {
                    CardCrawlGame.sound.play("UI_CLICK_1");
                    this.yesHb.clickStarted = true;
                }
                if (this.yesHb.clicked) {
                    this.yesHb.clicked = false;
                    save();
                }
            } else
                yesFontColor = Color.WHITE;
            if (CInputActionSet.proceed.isJustPressed()) {
                CInputActionSet.proceed.unpress();
                this.yesHb.clicked = true;
            }
        } else {
            yesButtonTexture = grayYesButton;
            yesFontColor = Color.GRAY;
        }
    }

    private void updateNo() {
        this.noHb.update();
        if (this.noHb.justHovered)
            CardCrawlGame.sound.play("UI_HOVER");
        if (noHb.hovered) {
            noFontColor = Color.GOLD;
            if (InputHelper.justClickedLeft) {
                CardCrawlGame.sound.play("UI_CLICK_1");
                this.noHb.clickStarted = true;
            }
        } else
            noFontColor = Color.WHITE;
        if (CInputActionSet.cancel.isJustPressed())
            this.noHb.clicked = true;
        if (this.noHb.clicked) {
            this.noHb.clicked = false;
            cancel();
        }
    }

    public void render(SpriteBatch sb) {
        if (shown) {
            renderBlackScreen(sb);
            renderBg(sb);
            renderSlots(sb);
            renderBgBoard(sb);
            renderProgressBar(sb);
            renderYesNoButton(sb);
        }
    }

    public void renderBg(SpriteBatch sb) {
        sb.setColor(screenColor);
        sb.draw(background, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
    }

    public void renderSlots(SpriteBatch sb) {
        for (Slot slot : slots)
            slot.render(sb);
    }

    public void renderYesNoButton(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        sb.draw(yesButtonTexture, yesButtonX, yesButtonY, yesButtonTexture.getWidth() * Settings.scale, yesButtonTexture.getHeight() * Settings.scale);
        FontHelper.renderFont(sb, FontHelper.topPanelInfoFont, TEXT[1], yesButtonX + 300.0F * Settings.scale, yesButtonY + 80.0F * Settings.scale, yesFontColor);
        sb.draw(noButtonTexture, noButtonX, noButtonY, noButtonTexture.getWidth() * Settings.scale, noButtonTexture.getHeight() * Settings.scale);
        FontHelper.renderFont(sb, FontHelper.topPanelInfoFont, TEXT[2], noButtonX + 180.0F * Settings.scale, noButtonY + 60.0F * Settings.scale, noFontColor);
    }

    public void renderBlackScreen(SpriteBatch sb) {
        Color c = Color.BLACK;
        c.a = 0.5F;
        sb.setColor(c);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, (float) Settings.WIDTH, (float) Settings.HEIGHT);
    }

    public void renderBgBoard(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        sb.draw(board, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
    }

    protected void renderProgressBar(SpriteBatch sb) {
        Color c = Color.WHITE;
        sb.setColor(c);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, progressBarStartX, progressBarStartY, this.progressBarWidth, 14.0F * Settings.scale);
        sb.setColor(new Color(1.0F, 0.8F, 0.3F, 1 * 0.9F));
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, progressBarStartX, progressBarStartY, this.progressBarWidth * this.progressPercent, 14.0F * Settings.scale);
        sb.setColor(new Color(0.0F, 0.0F, 0.0F, 1 * 0.25F));
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, progressBarStartX, progressBarStartY, this.progressBarWidth * this.progressPercent, 4.0F * Settings.scale);
        String derp = String.format(TEXT[0], collectedNumber, allNumber);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.topPanelInfoFont, derp, progressBarStartX, progressBarStartY - 12.0F * Settings.scale, c);
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
        if (this.prevLibrarySize != slots.size())
            calculateScrollBounds();
        resetScrolling();
        updateBarPosition();
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

    private void calculateScrollBounds() {
        if (slots.size() > 6) {
            int scrollTmp = slots.size() / NUM_PER_ROW - 2;
            if (slots.size() % NUM_PER_ROW != 0)
                scrollTmp++;
            this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT + scrollTmp * pad;
        } else {
            this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT;
        }
        this.prevLibrarySize = slots.size();
    }

    private void resetScrolling() {
        if (this.currentDiffY < this.scrollLowerBound) {
            this.currentDiffY = MathHelper.scrollSnapLerpSpeed(this.currentDiffY, this.scrollLowerBound);
        } else if (this.currentDiffY > this.scrollUpperBound) {
            this.currentDiffY = MathHelper.scrollSnapLerpSpeed(this.currentDiffY, this.scrollUpperBound);
        }
    }

    private boolean shouldShowScrollBar() {
        return (this.scrollUpperBound > SCROLL_BAR_THRESHOLD);
    }
}


