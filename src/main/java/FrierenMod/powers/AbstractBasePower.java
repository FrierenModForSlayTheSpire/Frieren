package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class AbstractBasePower extends AbstractPower {
    public String[] descriptions;
    public AbstractBasePower(String id, AbstractCreature owner, int amount, PowerType type){
        super();
        this.ID = id;
        this.owner = owner;
        this.amount = amount;
        this.type = type;
        this.name = CardCrawlGame.languagePack.getPowerStrings(id).NAME;
        this.descriptions = CardCrawlGame.languagePack.getPowerStrings(id).DESCRIPTIONS;
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ModInformation.makePowerPath(id.split(":")[1],84)), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ModInformation.makePowerPath(id.split(":")[1],32)), 0, 0, 32, 32);
        this.updateDescription();
    }
    public AbstractBasePower(String id, AbstractCreature owner, PowerType type){
        super();
        this.ID = id;
        this.owner = owner;
        this.amount = -1;
        this.type = type;
        this.name = CardCrawlGame.languagePack.getPowerStrings(id).NAME;
        this.descriptions = CardCrawlGame.languagePack.getPowerStrings(id).DESCRIPTIONS;
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ModInformation.makePowerPath(id.split(":")[1],84)), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ModInformation.makePowerPath(id.split(":")[1],32)), 0, 0, 32, 32);
        this.updateDescription();
    }
    public void afterChant(){}
}
