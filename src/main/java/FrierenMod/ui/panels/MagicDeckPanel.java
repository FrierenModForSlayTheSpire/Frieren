package FrierenMod.ui.panels;

import FrierenMod.patches.fields.MagicDeckField;
import FrierenMod.powers.DarkPower;
import FrierenMod.ui.screens.MagicDeckScreen;
import FrierenMod.utils.ModInformation;
import FrierenMod.utils.PublicRes;
import basemod.BaseMod;
import basemod.TopPanelItem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MagicDeckPanel extends TopPanelItem {
    public static final String ID = ModInformation.makeID(MagicDeckPanel.class.getSimpleName());
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;
    private static final float TOP_RIGHT_TIP_X = 1550.0F * Settings.scale;
    private static final float TIP_Y = Settings.HEIGHT - 120.0F * Settings.scale, TIP_OFF_X = 140.0F * Settings.scale, TIP_OFF_Y = 100.0F * Settings.scale;

    public MagicDeckPanel() {
        super(ImageMaster.loadImage(PublicRes.MAGIC_DECK_ICON), ID);
    }

    @Override
    protected void onClick() {
        AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
        if (po instanceof DarkPower && po.amount < 2) {
            return;
        }
        if (AbstractDungeon.screen == MagicDeckScreen.Enum.MAGIC_DECK_SCREEN) {
            CardCrawlGame.sound.play("DECK_CLOSE");
            AbstractDungeon.closeCurrentScreen();
            return;
        }
        switch (AbstractDungeon.screen) {
            case CARD_REWARD:
            case COMBAT_REWARD:
            case NONE:
                open();
                break;
            case MASTER_DECK_VIEW:
            case SETTINGS:
                AbstractDungeon.closeCurrentScreen();
                open();
                break;
            case DEATH:
                AbstractDungeon.deathScreen.hide();
                open();
                break;
            case BOSS_REWARD:
                AbstractDungeon.bossRelicScreen.hide();
                open();
                break;
            case SHOP:
                AbstractDungeon.overlayMenu.cancelButton.hide();
                open();
                break;
            case MAP:
                if (AbstractDungeon.dungeonMapScreen.dismissable)
                    AbstractDungeon.closeCurrentScreen();
                else
                    AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.MAP;
                open();
                break;
            case GRID:
                AbstractDungeon.gridSelectScreen.hide();
                open();
                break;
        }
    }

    private void open() {
        BaseMod.openCustomScreen(MagicDeckScreen.Enum.MAGIC_DECK_SCREEN);
    }

    protected void onHover() {
        AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
        if (po instanceof DarkPower && po.amount < 2) {
            return;
        }
        this.angle = MathHelper.angleLerpSnap(this.angle, 15.0F);
        this.tint.a = 0.25F;
        TipHelper.renderGenericTip(TOP_RIGHT_TIP_X, TIP_Y, TEXT[0], TEXT[1]);
    }

    @Override
    public void render(SpriteBatch sb) {
        AbstractPower po = AbstractDungeon.player.getPower(DarkPower.POWER_ID);
        if (po instanceof DarkPower && po.amount < 2) {
            return;
        }
        super.render(sb);
        FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelAmountFont,
                Integer.toString(MagicDeckField.getDeck().size()), this.x + 58.0F * Settings.scale, this.y + 25.0F * Settings.scale, Color.WHITE.cpy());
    }
}
