package FrierenMod.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class HimmelEffect extends AbstractGameEffect {
    private boolean playedSfx = false;
    public void update() {
        if (!this.playedSfx) {
            this.playedSfx = true;
            CardCrawlGame.sound.play("Himmel.mp3");
        }
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }
    @Override
    public void render(SpriteBatch spriteBatch) {

    }
    @Override
    public void dispose() {

    }
}
