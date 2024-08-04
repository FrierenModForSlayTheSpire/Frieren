package FrierenMod.ui.campfire;

import FrierenMod.effects.CampfireBuyPropEffect;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

public class BuyPropOption extends AbstractCampfireOption {
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ModInformation.makeID(BuyPropOption.class.getSimpleName())).TEXT;

    public BuyPropOption(boolean active) {
        this.label = TEXT[0];
        this.usable = active;
        this.description = TEXT[1];
        this.img = ImageMaster.loadImage("FrierenModResources/img/UI/campfire/buyProp.png");
    }
    @Override
    public void useOption() {
        if (this.usable)
            AbstractDungeon.effectList.add(new CampfireBuyPropEffect());
    }
}
