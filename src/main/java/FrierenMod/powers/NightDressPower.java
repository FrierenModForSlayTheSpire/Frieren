package FrierenMod.powers;

import FrierenMod.actions.ExhaustMagicPowerInDiscardPileAction;
import FrierenMod.actions.ExhaustMagicPowerInDrawPileAction;
import FrierenMod.actions.ExhaustMagicPowerInHandAction;
import FrierenMod.helpers.ChantHelper;
import FrierenMod.helpers.ModInfo;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class NightDressPower extends AbstractPower {
    // 能力的ID
    public static final String POWER_ID = ModInfo.makeID(NightDressPower.class.getSimpleName());
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int baseDamage;
    private int rate;

    public NightDressPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        this.rate = this.amount * 3;
        this.baseDamage = new ChantHelper().getAllMagicPowerNum() * this.rate;

        String path128 = "FrierenModResources/img/powers/Example84.png";
        String path48 = "FrierenModResources/img/powers/Example32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        this.updateDescription();
    }
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            this.updateDescription();
            this.flash();
            this.addToBot(new ExhaustMagicPowerInDrawPileAction(new ChantHelper().getMagicPowerNumInDrawPile()));
            this.addToBot(new ExhaustMagicPowerInHandAction(new ChantHelper().getMagicPowerNumInHand()));
            this.addToBot(new ExhaustMagicPowerInDiscardPileAction(new ChantHelper().getMagicPowerNumInDiscardPile()));
            this.addToBot(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(this.baseDamage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
        }

    }

    @Override
    public void onDrawOrDiscard() {
        this.updateDescription();
    }

    public void updateDescription() {
        this.rate = this.amount * 3;
        this.baseDamage = new ChantHelper().getAllMagicPowerNum() * this.rate;
        this.description = String.format(DESCRIPTIONS[0], this.rate, this.baseDamage);
    }

}