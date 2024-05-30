package FrierenMod.ui.panels;

import FrierenMod.cards.optionCards.ChantOptions.AbstractMagicFactor;
import FrierenMod.cards.optionCards.ChantOptions.MagicFactorAlpha;
import FrierenMod.ui.screens.MagicFactorDeckScreen;
import FrierenMod.utils.ModInformation;
import FrierenMod.utils.PublicRes;
import basemod.BaseMod;
import basemod.TopPanelItem;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;

public class MagicFactorDeckPanel extends TopPanelItem {
    public static final String ID = ModInformation.makeID(MagicFactorDeckPanel.class.getSimpleName());
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;
    private static final Texture icon = ImageMaster.loadImage(PublicRes.CHANT_BAG_ICON);
    private static final float TOP_RIGHT_TIP_X = 1550.0F * Settings.scale;
    private static final float TIP_Y = Settings.HEIGHT - 120.0F * Settings.scale, TIP_OFF_X = 140.0F * Settings.scale;

    public MagicFactorDeckPanel() {
        super(icon, ID);
    }

    @Override
    protected void onClick() {
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        tmp.addToTop(new MagicFactorAlpha(AbstractMagicFactor.ShowPlaceType.COMBAT));
        BaseMod.openCustomScreen(MagicFactorDeckScreen.Enum.MY_SCREEN);
    }

    protected void onHover() {
        this.angle = MathHelper.angleLerpSnap(this.angle, 15.0F);
        this.tint.a = 0.25F;
        TipHelper.renderGenericTip(TOP_RIGHT_TIP_X, TIP_Y, TEXT[0], TEXT[1]);
    }
}
