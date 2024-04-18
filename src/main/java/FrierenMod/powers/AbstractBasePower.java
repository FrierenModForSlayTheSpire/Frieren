package FrierenMod.powers;

import FrierenMod.utils.Config;
import FrierenMod.utils.ModInformation;
import FrierenMod.utils.PublicRes;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class AbstractBasePower extends AbstractPower {
    public String[] descriptions;

    public AbstractBasePower(String id, AbstractCreature owner, int amount, PowerType type) {
        super();
        this.ID = id;
        this.owner = owner;
        this.amount = amount;
        this.type = type;
        this.name = CardCrawlGame.languagePack.getPowerStrings(id).NAME;
        this.descriptions = CardCrawlGame.languagePack.getPowerStrings(id).DESCRIPTIONS;
        this.region128 = getImgTexture(id, 84);
        this.region48 = getImgTexture(id, 32);
    }

    public AbstractBasePower(String id, AbstractCreature owner, PowerType type) {
        super();
        this.ID = id;
        this.owner = owner;
        this.amount = -1;
        this.type = type;
        this.name = CardCrawlGame.languagePack.getPowerStrings(id).NAME;
        this.descriptions = CardCrawlGame.languagePack.getPowerStrings(id).DESCRIPTIONS;
        this.region128 = getImgTexture(id, 84);
        this.region48 = getImgTexture(id, 32);
    }

    private static TextureAtlas.AtlasRegion getImgTexture(String id, int size) {
        return new TextureAtlas.AtlasRegion(ImageMaster.loadImage(Config.IN_DEV ? ((size == 84) ? PublicRes.DEV_POWER_IMG_84 : PublicRes.DEV_POWER_IMG_32) : ModInformation.makePowerPath(id.split(":")[1], size)), 0, 0, size, size);
    }

    public void afterChant() {
    }

    public void afterChantFinished() {
    }
}
