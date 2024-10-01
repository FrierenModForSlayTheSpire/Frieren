package FrierenMod.ui.slot;

import FrierenMod.effects.SlotGlowBoarder;
import FrierenMod.patches.fields.CharacterSelectScreenField;
import FrierenMod.utils.Log;
import FrierenMod.utils.ModInformation;
import FrierenMod.utils.ResourceChecker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.GameCursor;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import static com.megacrit.cardcrawl.helpers.FontHelper.panelNameFont;
import static com.megacrit.cardcrawl.helpers.FontHelper.renderFontCentered;

public class Slot {
    public String id;
    public Texture img;
    public boolean hovered;
    public float hoverTimer;
    private float hoverDuration = 0.0F;
    private boolean renderTip;
    public Hitbox hb;
    public float current_x;
    public float current_y;
    public float target_x;
    public float target_y;
    public float drawScale;
    public float targetDrawScale;
    public float width;
    public float height;
    public int type;
    public Color renderColor;
    public float transparency;
    private static final Color glowColor = new Color(0.2F, 0.9F, 1.0F, 0.25F).cpy();
    public boolean isGlowing;
    private float glowTimer = 0.0F;
    public boolean locked;
    private ArrayList<SlotGlowBoarder> glowList = new ArrayList<>();
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ModInformation.makeID(Slot.class.getSimpleName())).TEXT;
    private static final Map<String, String> UNLOCK_TEXT = CardCrawlGame.languagePack.getUIString(ModInformation.makeID(Slot.class.getSimpleName())).TEXT_DICT;
    private static final Texture silhouette = ImageMaster.loadImage(ModInformation.makeUIPath("slotPreviewAndLibrary/bd_slot_silhouette"));
    private static final Color FRAME_SHADOW_COLOR = new Color(0.0F, 0.0F, 0.0F, 0.25F);
    private static final float SHADOW_OFFSET_X = 18.0F * Settings.scale;

    private static final float SHADOW_OFFSET_Y = 14.0F * Settings.scale;


    public Slot(String id, int type) {
        this.drawScale = Settings.scale;
        this.type = type;
        initialize(id);
    }

    public Slot(String id) {
        this.drawScale = 0.2F * Settings.scale;
        initialize(id);
    }

    public Slot(String id, boolean inDetail) {
        this.drawScale = 0.7F * Settings.scale;
        initialize(id);
    }

    private void initialize(String id) {
        this.id = id;
        String imgUrl = makeUrl(id);
        if (!ResourceChecker.exist(imgUrl)) {
            this.img = ImageMaster.loadImage(makeUrl("0001"));
            Log.logger.info("SlogBg is not Found:{}", imgUrl);
        }
        this.img = ImageMaster.loadImage(imgUrl);
        this.hoverTimer = 0.0F;
        this.width = drawScale * img.getWidth();
        this.height = drawScale * img.getHeight();
        this.renderTip = false;
        this.hb = new Hitbox(width, height);
        this.renderColor = Color.WHITE.cpy();
        this.transparency = 1.0F;
        this.locked = false;
    }

    public void setPosition(float x, float y) {
        this.target_x = x;
        this.target_y = y;
    }

    public void update() {
        if (this.hoverTimer != 0.0F) {
            this.hoverTimer -= Gdx.graphics.getDeltaTime();
            if (this.hoverTimer < 0.0F) {
                this.hoverTimer = 0.0F;
            }
        }
        if (Settings.FAST_MODE) {
            this.current_x = MathHelper.cardLerpSnap(this.current_x, this.target_x);
            this.current_y = MathHelper.cardLerpSnap(this.current_y, this.target_y);
        }

        this.current_x = MathHelper.cardLerpSnap(this.current_x, this.target_x);
        this.current_y = MathHelper.cardLerpSnap(this.current_y, this.target_y);
        this.hb.move(this.current_x, this.current_y);
        updateTransparency();
    }

    public void updateTransparency() {
        this.renderColor.a = this.transparency;
    }

    public void updateHoverLogicInPreview(CharacterSelectScreen screen, int index) {
        this.hb.update();
        if (this.hb.hovered) {
            this.hover();
            this.hoverDuration += Gdx.graphics.getDeltaTime();
            CardCrawlGame.cursor.changeType(GameCursor.CursorType.INSPECT);
            if (InputHelper.justClickedLeft) {
                InputHelper.justClickedLeft = false;
                this.unhover();
                CharacterSelectScreenField.slotBgLibrary.get(screen).show(this.id, index, CharacterSelectScreenField.slots.get(screen));
            }
        } else {
            this.unhover();
        }
    }

    public void updateHoverLogicInLibrary() {
        this.hb.update();
        if (this.hb.hovered) {
            this.hover();
            this.hoverDuration += Gdx.graphics.getDeltaTime();
            if (this.hoverDuration > 0.2F)
                this.renderTip = true;
            if (!locked) {
                this.targetDrawScale = 0.75F * Settings.scale;
                this.drawScale = MathHelper.cardScaleLerpSnap(this.drawScale, this.targetDrawScale);
            }
        } else {
            this.unhoverInLibrary();
        }
    }

    public void refreshSlot(String id) {
        this.id = id;
        this.img = ImageMaster.loadImage(makeUrl(id));
    }

    public void hover() {
        if (!this.hovered) {
            this.hovered = true;
        }
    }

    public void unhoverInLibrary() {
        if (this.hovered) {
            this.hovered = false;
            this.drawScale = 0.7F * Settings.scale;
            this.targetDrawScale = 0.7F * Settings.scale;
            this.renderTip = false;
            this.hoverDuration = 0.0F;
        }
    }

    public void unhover() {
        if (this.hovered) {
            this.hovered = false;
            this.hoverDuration = 0.0F;
            this.renderTip = false;
        }

    }

    public void beginGlowing() {
        this.isGlowing = true;
    }

    public void stopGlowing() {
        this.isGlowing = false;
        for (SlotGlowBoarder e : this.glowList)
            e.duration /= 5.0F;
    }

    private void updateGlow() {
        if (this.isGlowing) {
            this.glowTimer -= Gdx.graphics.getDeltaTime();
            if (this.glowTimer < 0.0F) {
                this.glowList.add(new SlotGlowBoarder(this, this.glowColor));
                this.glowTimer = 0.3F;
            }
        }

        for (Iterator<SlotGlowBoarder> i = this.glowList.iterator(); i.hasNext(); ) {
            SlotGlowBoarder e = i.next();
            e.update();
            if (e.isDone)
                i.remove();
        }
    }

    public void render(SpriteBatch sb) {
        if (!this.isOnScreen()) {
            return;
        }
        renderShadow(sb);
        updateGlow();
        renderGlow(sb);
        renderHelper(sb, this.img, this.current_x, this.current_y);
        renderSlotTipInLibrary(sb);
        if (this.drawScale == Settings.scale)
            renderTitle(sb, TEXT[type]);
        this.hb.render(sb);
    }

    public void renderShadow(SpriteBatch sb) {
        renderHelper(sb, FRAME_SHADOW_COLOR, silhouette, this.current_x + SHADOW_OFFSET_X * this.drawScale, this.current_y - SHADOW_OFFSET_Y * this.drawScale);
    }

    private void renderGlow(SpriteBatch sb) {
        for (AbstractGameEffect e : this.glowList)
            e.render(sb);
        sb.setBlendFunction(770, 771);
    }

    public void renderSlotTipInLibrary(SpriteBatch sb) {
        if (this.renderTip) {
            String unlockText = UNLOCK_TEXT.get(this.id);
            if (unlockText == null) {
                unlockText = UNLOCK_TEXT.get("EXCEPTION");
            }
            if (this.locked)
                unlockText += TEXT[5];
            else
                unlockText += TEXT[4];
            float drawX;
            float drawY = current_y + 200.0F;
            if (current_x > 1200 * Settings.scale) {
                drawX = current_x - 600.0F * drawScale;
            } else
                drawX = current_x + 250.0F * drawScale;
            TipHelper.renderGenericTip(drawX, drawY, TEXT[3], unlockText);
        }
    }

    public void setLockedInLibrary() {
        this.transparency = 0.4F;
        this.locked = true;
    }

    private void renderHelper(SpriteBatch sb, Texture img, float drawX, float drawY) {
        sb.setColor(renderColor);
        this.width = img.getWidth() * drawScale;
        this.height = img.getHeight() * drawScale;
        sb.draw(img, drawX - width / 2.0F, drawY - height / 2.0F, width, height);
    }

    private void renderHelper(SpriteBatch sb, Color color, Texture img, float drawX, float drawY) {
        sb.setColor(color);
        this.width = img.getWidth() * drawScale;
        this.height = img.getHeight() * drawScale;
        sb.draw(img, drawX - width / 2.0F, drawY - height / 2.0F, width, height);
    }

    private boolean isOnScreen() {
        return !(this.current_y < -200.0F * Settings.scale) && !(this.current_y > (float) Settings.HEIGHT + 200.0F * Settings.scale);
    }

    private void renderTitle(SpriteBatch sb, String title) {
        renderFontCentered(sb, panelNameFont, title, current_x, current_y + 200.0F * drawScale, Color.WHITE.cpy());
    }

    private static String makeUrl(String id) {
        return ModInformation.makeUIPath("slotBg/" + id);
    }
}
