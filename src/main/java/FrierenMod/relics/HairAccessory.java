package FrierenMod.relics;

import FrierenMod.utils.ModInformation;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import FrierenMod.cards.AbstractFrierenCard;

public class HairAccessory extends CustomRelic {
    public static final String ID = ModInformation.makeID("HairAccessory");
    // 图片路径
    private static final String IMG_PATH = "FrierenModResources/img/relics/HairAccessory.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.RARE;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public HairAccessory() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new HairAccessory();
    }

    public void atBattleStart() {
        this.counter = 0;
    }
    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card instanceof AbstractFrierenCard && ((AbstractFrierenCard) card).isMagicPower){
            this.counter++;
            if (this.counter >= 4) {
                this.counter = 0;
                flash();
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DrawCardAction((AbstractCreature)AbstractDungeon.player, 1));
            }
        }
    }

    public void onVictory() {
        this.counter = -1;
    }
}