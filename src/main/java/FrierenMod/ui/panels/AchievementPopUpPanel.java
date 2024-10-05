package FrierenMod.ui.panels;

import FrierenMod.ui.slot.Slot;
import FrierenMod.utils.Config;
import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class AchievementPopUpPanel {
    public Slot slot;
    public boolean ended;
    private static final float WAIT_TIME = 2.0f;
    private float timer = 0.0f;
    private static final Texture BACKGROUND = ImageMaster.loadImage(ModInformation.makeUIPath("achievement/background"));
    private static final float ORIGIN_START_X = Settings.WIDTH / 2.0F;
    private static final float ORIGIN_START_Y = Settings.HEIGHT + BACKGROUND.getHeight() * Settings.scale;
    private static final float ORIGIN_END_X = Settings.WIDTH / 2.0F;
    private static final float ORIGIN_END_Y = Settings.HEIGHT - BACKGROUND.getHeight() * Settings.scale + 150.0F * Settings.scale;
    private static final float DRAW_SCALE = 0.6F * Settings.scale;
    private float startX, startY;
    private float endX, endY;
    private float currentX, currentY;
    private float speed;
    private static final float height = BACKGROUND.getHeight() * DRAW_SCALE;
    private float width;

    private enum State {MOVING_TO_TARGET, WAITING, MOVING_BACK}

    private State state;
    private boolean playedSfx;

    public AchievementPopUpPanel(String newSlotBgId) {
        this.ended = false;
        this.currentX = startX = ORIGIN_START_X;
        this.currentY = startY = ORIGIN_START_Y;
        this.endX = ORIGIN_END_X;
        this.endY = ORIGIN_END_Y;
        this.slot = new Slot(newSlotBgId);
        this.state = State.MOVING_TO_TARGET;
        this.speed = 2.0f;
        this.playedSfx = false;
        initializeBackgroundWidth();
    }

    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        if (!this.playedSfx) {
            this.playedSfx = true;
            if (Config.ALLOW_SPECIAL_SFX)
                CardCrawlGame.sound.play("achievement.mp3");
        }
        switch (state) {
            case MOVING_TO_TARGET:
                this.speed += 10 * Gdx.graphics.getDeltaTime();
                if (this.speed > 3.0F) {
                    this.speed = 3.0F;
                }
                currentX = MathUtils.lerp(currentX, endX, speed * deltaTime);
                currentY = MathUtils.lerp(currentY, endY, speed * deltaTime);
                if (Math.abs(currentX - endX) < 1f && Math.abs(currentY - endY) < 1f) {
                    currentX = endX;
                    currentY = endY;
                    state = State.WAITING;
                    timer = 0.0f;
                }
                break;
            case WAITING:
                timer += deltaTime;
                if (timer >= WAIT_TIME) {
                    state = State.MOVING_BACK;
                }
                break;

            case MOVING_BACK:
                this.speed = 3.0F;
                currentX = MathUtils.lerp(currentX, startX, speed * deltaTime);
                currentY = MathUtils.lerp(currentY, startY, speed * deltaTime);

                if (Math.abs(currentX - startX) < 1f && Math.abs(currentY - startY) < 1f) {
                    currentX = startX;
                    currentY = startY;
                    state = State.MOVING_TO_TARGET;
                    ended = true;
                }
                break;
        }
        this.slot.updateWithAchievementPanel(this.currentX - 0.5f * width + 80.0f * Settings.scale, this.currentY);
    }


    public void render(SpriteBatch sb) {
        if (!isOnScreen())
            return;
        renderHelper(sb, Color.WHITE, BACKGROUND, currentX, currentY);
        this.slot.render(sb);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipHeaderFont, slot.title, currentX - 0.5f * width + slot.width + 60.0f * Settings.scale, currentY + 40.0F * Settings.scale, Settings.GOLD_COLOR.cpy());
        FontHelper.renderSmartText(sb, FontHelper.tipBodyFont, slot.unlockText, currentX - 0.5f * width + slot.width + 60.0f * Settings.scale, currentY, width * 0.6F, 25.0F * Settings.scale, Settings.CREAM_COLOR);
    }

    private void renderHelper(SpriteBatch sb, Color color, Texture img, float drawX, float drawY) {
        sb.setColor(color);
        sb.draw(img, drawX - width / 2.0F, drawY - height / 2.0F, width, height);
    }

    private boolean isOnScreen() {
        return !(this.currentY < -200.0F * Settings.scale) && !(this.currentY > (float) Settings.HEIGHT + 200.0F * Settings.scale);
    }

    private void initializeBackgroundWidth() {
        switch (Settings.language) {
            case ZHS:
                this.width = BACKGROUND.getWidth() * DRAW_SCALE + Math.max(0, this.slot.title.length() - 5) * 25.0F * DRAW_SCALE;
                break;
            default:
            case ENG:
                break;
        }
    }
}
