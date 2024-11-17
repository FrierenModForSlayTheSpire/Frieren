package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import FrierenMod.utils.PublicRes;
import FrierenMod.utils.ResourceChecker;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
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

    public AbstractBasePower(String id, AbstractCreature owner, int amt, boolean invisiblePower) {
        super();
        this.ID = id;
        this.owner = owner;
        this.amount = amt;
        this.type = PowerType.BUFF;
    }

    public static TextureAtlas.AtlasRegion getImgTexture(String id, int size) {
        if (ResourceChecker.exist(ModInformation.makePowerPath(id.split(":")[1], size))) {
            return new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ModInformation.makePowerPath(id.split(":")[1], size)), 0, 0, size, size);
        }
        return new TextureAtlas.AtlasRegion(ImageMaster.loadImage(((size == 84) ? PublicRes.DEV_POWER_IMG_84 : PublicRes.DEV_POWER_IMG_32)), 0, 0, size, size);
    }

    public void afterChant() {
    }

    public void afterChantFinished() {
    }

    public void beforeGainSpecializedOffensiveMagic(AbstractCard magic) {

    }
    public void afterGainSpecializedOffensiveMagic(AbstractCard magic) {

    }
    public int modifyRaidTriggerTimes(){
        return 0;
    }
    public int modifyRaidTriggerTimes(boolean bool){
        return 0;
    }
    public void afterRaidTriggered(){

    }
    public void afterRaidFinished(){

    }
}
