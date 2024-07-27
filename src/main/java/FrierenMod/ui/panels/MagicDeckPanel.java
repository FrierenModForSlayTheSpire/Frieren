package FrierenMod.ui.panels;

import FrierenMod.ui.screens.MagicDeckScreen;
import FrierenMod.utils.ModInformation;
import FrierenMod.utils.PublicRes;
import basemod.BaseMod;
import basemod.TopPanelItem;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;

public class MagicDeckPanel extends TopPanelItem {
    public static final String ID = ModInformation.makeID(MagicDeckPanel.class.getSimpleName());
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;
    private static final float TOP_RIGHT_TIP_X = 1550.0F * Settings.scale;
    private static final float TIP_Y = Settings.HEIGHT - 120.0F * Settings.scale, TIP_OFF_X = 140.0F * Settings.scale;

    public MagicDeckPanel() {
        super(ImageMaster.loadImage(PublicRes.MAGIC_BAG_ICON), ID);
    }

    @Override
    protected void onClick() {
        BaseMod.openCustomScreen(MagicDeckScreen.Enum.MAGIC_FACTOR_SCREEN);
    }

    protected void onHover() {
        this.angle = MathHelper.angleLerpSnap(this.angle, 15.0F);
        this.tint.a = 0.25F;
        TipHelper.renderGenericTip(TOP_RIGHT_TIP_X, TIP_Y, TEXT[0], TEXT[1]);
    }
}
