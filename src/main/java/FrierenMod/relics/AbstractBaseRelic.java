package FrierenMod.relics;

import FrierenMod.utils.ModInformation;
import FrierenMod.utils.ResourceChecker;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public abstract class AbstractBaseRelic extends CustomRelic {
    public AbstractBaseRelic(String id, Texture texture, RelicTier tier, LandingSound sfx) {
        super(id, texture, tier, sfx);
    }

    public AbstractBaseRelic(String id, RelicTier tier) {
        super(id, getImgTexture(id), tier, LandingSound.FLAT);
    }

    private static Texture getImgTexture(String id) {
        if (ResourceChecker.exist(ModInformation.makeRelicImgPath(id.split(":")[1]))) {
            return ImageMaster.loadImage(ModInformation.makeRelicImgPath(id.split(":")[1]));
        }
        return ImageMaster.loadImage(ModInformation.makeRelicImgPath("Beta"));
    }
}
