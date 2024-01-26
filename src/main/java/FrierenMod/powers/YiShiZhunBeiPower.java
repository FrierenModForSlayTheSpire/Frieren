package FrierenMod.powers;

import FrierenMod.helpers.ModHelper;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static FrierenMod.tags.CustomTags.RECAST;

public class YiShiZhunBeiPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.makePath(YiShiZhunBeiPower.class.getSimpleName());
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int recastCardPlayedNum = 0;
    public YiShiZhunBeiPower(AbstractCreature owner,int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        this.amount = amount;
        String path128 = "FrierenModResources/img/powers/Example84.png";
        String path48 = "FrierenModResources/img/powers/Example32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        this.updateDescription();
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        super.onAfterCardPlayed(usedCard);
        if(usedCard.tags.contains(RECAST)){
            this.recastCardPlayedNum++;
        }
        if(this.recastCardPlayedNum == 5){
            this.flash();
            this.addToBot(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(this.amount * 8, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
            this.recastCardPlayedNum = 0;
        }
        this.updateDescription();
    }
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount * 8, this.recastCardPlayedNum);
    }
}
