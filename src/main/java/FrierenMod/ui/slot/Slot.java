package FrierenMod.ui.slot;

import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.*;

import static com.megacrit.cardcrawl.helpers.FontHelper.*;

public class Slot {
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
    public static final float drawScale = 1.0F * Settings.scale;
    public float width;
    public float height;
    public int type;
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ModInformation.makeID(Slot.class.getSimpleName())).TEXT;


    public Slot(String imgUrl, int type) {
        this.img = ImageMaster.loadImage(imgUrl);
        this.hoverTimer = 0.0F;
        this.width = drawScale * img.getWidth();
        this.height = drawScale * img.getHeight();
        this.hb = new Hitbox(width, height);
        this.type = type;
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
    }

    public void updateHoverLogic() {
        this.hb.update();
        if (this.hb.hovered) {
            this.hover();
            this.hoverDuration += Gdx.graphics.getDeltaTime();
            if (this.hoverDuration > 0.2F && !Settings.hideCards) {
                this.renderTip = true;
            }
        } else {
            this.unhover();
        }
    }

    public void hover() {
        if (!this.hovered) {
            this.hovered = true;
        }

    }

    public void unhover() {
        if (this.hovered) {
            this.hovered = false;
            this.hoverDuration = 0.0F;
            this.renderTip = false;
        }

    }

    public void render(SpriteBatch sb) {
        if (!this.isOnScreen()) {
            return;
        }
        renderHelper(sb, this.img, this.current_x, this.current_y);
        renderTitle(sb, TEXT[type]);
        this.hb.render(sb);
    }

//    public void renderTip() {
//        if (this.renderTip) {
//            TipHelper.renderGenericTip(current_x + 200.0F * drawScale, current_y + 200.0F * drawScale, "test", "test");
//        }
//    }

    private void renderHelper(SpriteBatch sb, Texture img, float drawX, float drawY) {
        sb.setColor(Color.WHITE);
        sb.draw(img, drawX - width / 2.0F, drawY - height / 2.0F, width, height);
    }

    private boolean isOnScreen() {
        return !(this.current_y < -200.0F * Settings.scale) && !(this.current_y > (float) Settings.HEIGHT + 200.0F * Settings.scale);
    }

    private void renderTitle(SpriteBatch sb, String title) {
        renderFontCentered(sb, panelNameFont, title, current_x, current_y + 200.0F * drawScale, Color.WHITE.cpy());
    }

}
