package FrierenMod.powers;

import FrierenMod.gameHelpers.ChantHelper;
import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MagicShowPower extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = ModInformation.makeID(MagicShowPower.class.getSimpleName());
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MagicShowPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = -1;
        String path128 = "FrierenModResources/img/powers/Example84.png";
        String path48 = "FrierenModResources/img/powers/Example32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        this.updateDescription();
    }

    @Override
    public void onInitialApplication() {
        this.updateDescription();
    }
    @Override
    public void onDrawOrDiscard() {
        this.updateDescription();
    }
    @Override
    public void atStartOfTurnPostDraw() {
        this.updateDescription();
    }
    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        this.updateDescription();
    }
    public void updateDescription() {
        int hand = new ChantHelper().getMagicPowerNumInHand();
        int draw = new ChantHelper().getMagicPowerNumInDrawPile();
        int discard = new ChantHelper().getMagicPowerNumInDiscardPile();
        this.description = String.format(DESCRIPTIONS[0], draw, hand ,discard);
    }
}