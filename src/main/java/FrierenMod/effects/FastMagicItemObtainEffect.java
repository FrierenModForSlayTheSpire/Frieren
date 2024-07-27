package FrierenMod.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class FastMagicItemObtainEffect extends AbstractGameEffect {
    private final AbstractCard card;

    public FastMagicItemObtainEffect(AbstractCard card, float x, float y) {
        this.card = card;
        this.duration = 0.01F;
        card.current_x = x;
        card.current_y = y;
        CardCrawlGame.sound.play("CARD_SELECT");
    }

    public void update() {
        if (this.isDone)
            return;
        this.duration -= Gdx.graphics.getDeltaTime();
        this.card.update();
        if (this.duration < 0.0F) {
            for (AbstractRelic r : AbstractDungeon.player.relics)
                r.onObtainCard(this.card);
            this.isDone = true;
            this.card.shrink();
            (AbstractDungeon.getCurrRoom()).souls.obtain(this.card, true);
        }
    }

    public void render(SpriteBatch sb) {
        if (!this.isDone)
            this.card.render(sb);
    }

    public void dispose() {}
}

