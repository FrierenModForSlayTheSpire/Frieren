package FrierenMod.powers;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.white.MagicianChoke;
import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;



public class MagicianChokePower extends AbstractPower {
    public static final String POWER_ID = ModInformation.makeID(MagicianChokePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MagicianChokePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.DEBUFF;
        this.amount = amount;
        String path128 = "FrierenModResources/img/powers/Example84.png";
        String path48 = "FrierenModResources/img/powers/Example32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void atStartOfTurn() {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "MagicianChoked"));
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractFrierenCard && ((AbstractFrierenCard) card).isMagicPower) {
            flash();
            this.addToBot(new LoseHPAction(this.owner, null, this.amount));
        }
    }
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

}