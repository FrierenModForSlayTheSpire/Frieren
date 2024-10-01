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

import java.util.Map;

public class AchievementPopUpPanel {
    public String newSlotBgId;
    public Slot slot;
    public String description;
    public boolean ended;
    public float animateTimer = 3.0F;
    private static final float WAIT_TIME = 2.0f;  // 等待3秒
    private float timer = 0.0f;     // 用于计时停留
    private static final Texture background = ImageMaster.loadImage("FrierenModResources/img/UI/achievement/background.png");
    private static final String TITLE = CardCrawlGame.languagePack.getUIString(ModInformation.makeID(Slot.class.getSimpleName())).TEXT[6];
    private static final Map<String, String> TEXT_DICT = CardCrawlGame.languagePack.getUIString(ModInformation.makeID(Slot.class.getSimpleName())).TEXT_DICT;
    private static final float ORIGIN_START_X = Settings.WIDTH / 2.0F;
    private static final float ORIGIN_START_Y = Settings.HEIGHT + background.getHeight() * Settings.scale;
    private static final float ORIGIN_END_X = Settings.WIDTH / 2.0F;
    private static final float ORIGIN_END_Y = Settings.HEIGHT - background.getHeight() * Settings.scale + 150.0F * Settings.scale;
    private static final float DRAW_SCALE = 0.6F * Settings.scale;
    private float startX, startY;
    private float endX, endY;
    private float currentX, currentY;
    private float speed;

    private enum State {MOVING_TO_TARGET, WAITING, MOVING_BACK}

    private State state;
    private boolean playedSfx;

    public AchievementPopUpPanel(String newSlotBgId) {
        this.ended = false;
        this.currentX = startX = ORIGIN_START_X;
        this.currentY = startY = ORIGIN_START_Y;
        this.endX = ORIGIN_END_X;
        this.endY = ORIGIN_END_Y;
        this.newSlotBgId = newSlotBgId;
        this.slot = new Slot(newSlotBgId);
        this.description = TEXT_DICT.get(newSlotBgId);
        if (description == null)
            description = "ERROR!";
        this.animateTimer = 3.0F;
        this.state = State.MOVING_TO_TARGET;
        this.speed = 2.0f;
        this.playedSfx = false;
    }

    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        if (!this.playedSfx) {
            this.playedSfx = true;
            if(Config.ALLOW_SPECIAL_SFX)
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
                    state = State.MOVING_BACK;  // 进入移动回原点状态
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
        this.slot.updateWithAchievementPanel(this.currentX - 100.0F * Settings.scale, this.currentY);
    }


    public void render(SpriteBatch sb) {
        if (!isOnScreen())
            return;
        renderHelper(sb, Color.WHITE, background, currentX, currentY);
        this.slot.render(sb);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipHeaderFont, TITLE, currentX - 60.0F * Settings.scale, currentY + 40.0F * Settings.scale, Settings.GOLD_COLOR.cpy());
        FontHelper.renderSmartText(sb, FontHelper.tipBodyFont, description, currentX - 60.0F * Settings.scale, currentY, background.getWidth() * DRAW_SCALE * 0.6F, 25.0F * Settings.scale, Settings.CREAM_COLOR);
    }

    private void renderHelper(SpriteBatch sb, Color color, Texture img, float drawX, float drawY) {
        sb.setColor(color);
        float width = img.getWidth() * DRAW_SCALE;
        float height = img.getHeight() * DRAW_SCALE;
        sb.draw(img, drawX - width / 2.0F, drawY - height / 2.0F, width, height);
    }

    private boolean isOnScreen() {
        return !(this.currentY < -200.0F * Settings.scale) && !(this.currentY > (float) Settings.HEIGHT + 200.0F * Settings.scale);
    }
}
