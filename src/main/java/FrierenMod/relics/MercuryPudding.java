package FrierenMod.relics;

import FrierenMod.utils.ModInformation;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.characters.AbstractPlayer;


public class MercuryPudding extends CustomRelic {
    public static final String ID = ModInformation.makeID("MercuryPudding");
    // 图片路径
    private static final String IMG_PATH = "FrierenModResources/img/relics/MercuryPudding.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.UNCOMMON;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public MercuryPudding() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
    @Override
    public void setCounter(int setCounter) {
        if (setCounter == -2) {
            usedUp();
            this.counter = -2;
        }
    }

    public void onVictory() {
        flash();
        addToTop((AbstractGameAction) new RelicAboveCreatureAction((AbstractCreature) AbstractDungeon.player, this));
        AbstractPlayer p = AbstractDungeon.player;
        if (p.currentHealth <= 10) {
            int healAmt =p.maxHealth/2;
            AbstractDungeon.player.heal(healAmt, true);
            this.setCounter(-2);
        }
    }
    public AbstractRelic makeCopy() {
        return new MercuryPudding();
    }
}