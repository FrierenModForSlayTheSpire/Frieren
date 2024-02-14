package FrierenMod.relics;

import FrierenMod.utils.ModInformation;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public abstract class AbstractFrierenRelic extends CustomRelic {
    public AbstractFrierenRelic(String id, Texture texture, RelicTier tier, LandingSound sfx) {
        super(id, texture, tier, sfx);
    }
    public AbstractFrierenRelic(String id, RelicTier tier) {
        super(id, ImageMaster.loadImage(ModInformation.makeRelicImgPath(id.split(":")[1])), tier, LandingSound.FLAT);
    }
}
