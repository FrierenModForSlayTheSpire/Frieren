package FrierenMod.powers;

import FrierenMod.actions.ReceiveEverythingAction;
import FrierenMod.helpers.ModInfo;
import FrierenMod.helpers.Status;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class TimeTravelPower extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = ModInfo.makeID(TimeTravelPower.class.getSimpleName());
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private final Status status;
    private boolean isChanged;

    private int counts = 2;
    public TimeTravelPower(AbstractCreature owner, Status status, boolean isChanged) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        this.amount = -1;

        String path128 = "FrierenModResources/img/powers/Example84.png";
        String path48 = "FrierenModResources/img/powers/Example32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.status = status;
        this.isChanged = isChanged;
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0],this.counts);
    }

    @Override
    public void atStartOfTurn() {
        counts--;
        this.updateDescription();
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            if(counts == 0){
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
                this.addToBot(new ReceiveEverythingAction(this.status,this.isChanged));
            }
        }
    }
}