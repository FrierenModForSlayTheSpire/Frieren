package FrierenMod.relics;

import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class HolyEmblem extends CustomRelic {
    public static final String ID = ModInformation.makeID(HolyEmblem.class.getSimpleName());
    // 图片路径
    private static final String IMG_PATH = ModInformation.makeRelicImgPath(HolyEmblem.class.getSimpleName());
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public HolyEmblem() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new HolyEmblem();
    }
    @Override
    public void atTurnStartPostDraw() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new MakeTempCardInDrawPileAction(new MagicPower(),3,true,true));
    }
}
