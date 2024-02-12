package FrierenMod.relics;

import FrierenMod.helpers.ModInfo;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;



public class MimicHead extends CustomRelic {
    public static final String ID = ModInfo.makeID("MimicHead");
    // 图片路径
    private static final String IMG_PATH = "FrierenModResources/img/relics/MimicHead.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.UNCOMMON;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public MimicHead() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    public void onChestOpen(boolean bossChest) {
        if (!bossChest) {
            flash();
            addToTop((AbstractGameAction) new RelicAboveCreatureAction((AbstractCreature) AbstractDungeon.player, this));
            AbstractDungeon.getCurrRoom().addGoldToRewards(100);
        }
    }

    public AbstractRelic makeCopy() {
        return new MimicHead();
    }
}