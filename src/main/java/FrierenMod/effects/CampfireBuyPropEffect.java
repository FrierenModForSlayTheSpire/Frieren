package FrierenMod.effects;

import FrierenMod.cards.optionCards.magicItems.AbstractMagicItem;
import FrierenMod.gameHelpers.CardPoolHelper;
import FrierenMod.patches.fields.ForBuyPropField;
import FrierenMod.ui.campfire.BuyPropOption;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class CampfireBuyPropEffect extends AbstractGameEffect {

    public static final String[] TEXT = BuyPropOption.TEXT;

    private static final int PROP_NUM = 2;

    private boolean openedScreen = false;

    private final Color screenColor = AbstractDungeon.fadeColor.cpy();

    public CampfireBuyPropEffect() {
        this.duration = 1.5F;
        this.screenColor.a = 0.0F;
        AbstractDungeon.overlayMenu.proceedButton.hide();
    }

    public void update() {
        if (!AbstractDungeon.isScreenUp) {
            this.duration -= Gdx.graphics.getDeltaTime();
            updateBlackScreenColor();
        }
        if (!AbstractDungeon.isScreenUp && AbstractDungeon.gridSelectScreen.selectedCards.size() >= PROP_NUM && ForBuyPropField.forBuyProp.get(AbstractDungeon.gridSelectScreen)) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                AbstractDungeon.topLevelEffects.add(new FastMagicItemObtainEffect(c));
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
        if (this.duration < 1.0F && !this.openedScreen) {
            this.openedScreen = true;
            AbstractDungeon.gridSelectScreen.open(
                    CardPoolHelper.getMagicItemCardGroup(AbstractMagicItem.MagicItemRarity.PROP), PROP_NUM, TEXT[1], false, false, true, false);
            AbstractDungeon.overlayMenu.cancelButton.show(TEXT[2]);
            ForBuyPropField.forBuyProp.set(AbstractDungeon.gridSelectScreen, true);
        }
        if (this.duration < 0.0F) {
            this.isDone = true;
            if (CampfireUI.hidden) {
                AbstractRoom.waitTimer = 0.0F;
                (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
                ((RestRoom) AbstractDungeon.getCurrRoom()).cutFireSound();
            }
        }
    }

    private void updateBlackScreenColor() {
        if (this.duration > 1.0F) {
            this.screenColor.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 1.0F) * 2.0F);
        } else {
            this.screenColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / 1.5F);
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.screenColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID)
            AbstractDungeon.gridSelectScreen.render(sb);
    }

    public void dispose() {
    }
}
