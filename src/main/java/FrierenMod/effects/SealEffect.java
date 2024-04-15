package FrierenMod.effects;

import FrierenMod.panels.ConfigPanel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SealEffect extends AbstractGameEffect {
    public static final Logger logger = LogManager.getLogger(SealEffect.class.getName());
    public AbstractCard card;
    private boolean justStart = true;
    private boolean skipMove = false;
    public static float DURATION = Settings.ACTION_DUR_FAST;
    public SealEffect(AbstractCard card) {
        this(card, false);
    }

    public SealEffect(AbstractCard card, boolean skipMove) {
        this.card = card;
        if (card == AbstractDungeon.player.hoveredCard)
            AbstractDungeon.player.releaseCard();
        AbstractDungeon.actionManager.removeFromQueue(card);
        card.unhover();
        card.untip();
        card.stopGlowing();
        this.duration = this.startingDuration = DURATION;
        this.skipMove = skipMove;
    }

    public void update() {
        if(!ConfigPanel.ALLOW_SPECIAL_SFX){
            this.isDone = true;
            return;
        }
        if (this.justStart) {
            this.justStart = false;
            this.card.untip();
            this.card.unhover();
            this.card.setAngle(0.0F);
            this.card.targetDrawScale = 0.001F;
            this.card.target_x = AbstractDungeon.player.hb.cX;
            this.card.target_y = AbstractDungeon.player.hb.cY;
            if (this.skipMove) {
                this.card.current_x = this.card.target_x;
                this.card.current_y = this.card.target_y;
                this.card.drawScale = this.card.targetDrawScale;
                this.isDone = true;
                return;
            }
        }
        this.card.update();
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F)
            this.isDone = true;
    }

    public void render(SpriteBatch sb) {
        if (!this.isDone && !this.skipMove)
            this.card.render(sb);
    }

    public void dispose() {}
}
