package FrierenMod.ui.panels;

import FrierenMod.utils.ModInformation;
import FrierenMod.utils.PublicRes;
import basemod.TopPanelItem;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class ChantOptionCardsDeckPanel extends TopPanelItem {
    public static final String ID = ModInformation.makeID(ChantOptionCardsDeckPanel.class.getSimpleName());
    private static final Texture icon = ImageMaster.loadImage(PublicRes.CHANT_BAG_ICON);

    public ChantOptionCardsDeckPanel() {
        super(icon, ID);
    }

    @Override
    protected void onClick() {

    }
}
