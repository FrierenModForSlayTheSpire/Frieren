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
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.ArrayList;
import java.util.Iterator;

import static com.megacrit.cardcrawl.helpers.FontHelper.panelNameFont;
import static com.megacrit.cardcrawl.helpers.FontHelper.renderFontCentered;

public class Slot {
    public String id;
    public Texture img;
    public boolean hovered;
    public float hoverTimer;
    private float hoverDuration;
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
        if (this.hb.hovered && !locked) {
            this.hover();
            this.targetDrawScale = 0.75F * Settings.scale;
            this.drawScale = MathHelper.cardScaleLerpSnap(this.drawScale, this.targetDrawScale);
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
        updateGlow();
        renderGlow(sb);
        renderHelper(sb, this.img, this.current_x, this.current_y);
        if (this.drawScale == Settings.scale)
            renderTitle(sb, TEXT[type]);
        this.hb.render(sb);
    }

    private void renderGlow(SpriteBatch sb) {
        for (AbstractGameEffect e : this.glowList)
            e.render(sb);
        sb.setBlendFunction(770, 771);
    }

    public void setLockedInLibrary() {
        this.transparency = 0.5F;
        this.locked = true;
    }


//    public void renderTip() {
//        if (this.renderTip) {
//            TipHelper.renderGenericTip(current_x + 200.0F * drawScale, current_y + 200.0F * drawScale, "test", "test");
//        }
//    }

    private void renderHelper(SpriteBatch sb, Texture img, float drawX, float drawY) {
        sb.setColor(renderColor);
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
