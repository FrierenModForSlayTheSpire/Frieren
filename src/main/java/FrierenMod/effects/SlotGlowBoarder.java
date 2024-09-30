package FrierenMod.effects;

import FrierenMod.ui.slot.Slot;
import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class SlotGlowBoarder extends AbstractGameEffect {
    private Slot slot;

    private Texture img;

    private float scale;

    public SlotGlowBoarder(Slot slot) {
        this(slot, Color.valueOf("30c8dcff"));
    }

    public SlotGlowBoarder(Slot slot, Color gColor) {
        this.slot = slot;
        this.duration = 1.2F;
        this.img = ImageMaster.loadImage(ModInformation.makeUIPath("slotPreviewAndLibrary/bd_slot_silhouette"));
        this.color = gColor.cpy();
    }

    public void update() {
        this.scale = (1.0F + Interpolation.pow2Out.apply(0.03F, 0.11F, 1.0F - this.duration)) * this.slot.drawScale;
        this.color.a = this.duration / 2.0F;
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
            this.duration = 0.0F;
        }
    }

    public void render(SpriteBatch sb) {

        renderHelper(sb, img, this.slot.current_x, this.slot.current_y);
//        sb.draw(this.img, this.slot.current_x - this.slot.width / 2.0F - 10.0F * scale, this.slot.current_y - this.slot.height / 2.0F - 10.0F * scale, this.img.getWidth() * scale, this.img.getHeight() * scale);
    }

    private void renderHelper(SpriteBatch sb, Texture img, float drawX, float drawY) {
        sb.setColor(this.color);
        sb.draw(img, drawX - img.getWidth() * scale / 2.0F, drawY - img.getHeight() * scale / 2.0F, img.getWidth() * scale, img.getHeight() * scale);
    }

    public void dispose() {
    }
}
