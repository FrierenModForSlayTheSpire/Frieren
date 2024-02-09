package FrierenMod.powers;

import FrierenMod.cardMods.FastMagicPowerMod;
import FrierenMod.cardMods.FinalFastMagicPowerMod;
import FrierenMod.cardMods.FinalMagicPowerMod;
import FrierenMod.cardMods.MagicPowerMod;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.helpers.ModInfo;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static FrierenMod.tags.CustomTags.*;

public class SpeedFlowPower extends AbstractPower {
    public static final String POWER_ID = ModInfo.makeID(SpeedFlowPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private final AbstractPlayer p = AbstractDungeon.player;

    public SpeedFlowPower(AbstractCreature owner) {
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
        upgradeAllMagicPower();
    }
    @Override
    public void onDrawOrDiscard() {
        upgradeAllMagicPower();
    }
    @Override
    public void atStartOfTurnPostDraw() {
        upgradeAllMagicPower();
    }
    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        upgradeAllMagicPower();
    }
    public void atEndOfTurn(boolean isPlayer) {
        degradeMagicPower();
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
    private void upgradeAllMagicPowerInGroup(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group) {
            if(c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isMagicPower){
                if (!c.tags.contains(FINAL_MAGIC_POWER) && !c.tags.contains(FAST_MAGIC_POWER)) {
                    if (cardGroup.type == CardGroup.CardGroupType.HAND) {
                        c.superFlash();
                    }
                    CardModifierManager.addModifier(c, new FastMagicPowerMod());
                    c.applyPowers();
                } else if (!c.tags.contains(FAST_MAGIC_POWER) && c.tags.contains(FINAL_MAGIC_POWER)) {
                    if (cardGroup.type == CardGroup.CardGroupType.HAND) {
                        c.superFlash();
                    }
                    CardModifierManager.addModifier(c, new FinalFastMagicPowerMod());
                    c.applyPowers();
                }
            }
        }
    }
    private void upgradeAllMagicPower(){
        upgradeAllMagicPowerInGroup(p.drawPile);
        upgradeAllMagicPowerInGroup(p.hand);
        upgradeAllMagicPowerInGroup(p.discardPile);
        upgradeAllMagicPowerInGroup(p.exhaustPile);
    }
    private void degradeMagicPowerInGroup(CardGroup cardGroup){
        for (AbstractCard c : cardGroup.group) {
            if(c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isMagicPower && c.tags.contains(FAST_MAGIC_POWER)){
                if (c.tags.contains(FINAL_MAGIC_POWER)) {
                    CardModifierManager.addModifier(c, new FinalMagicPowerMod());
                }else {
                    CardModifierManager.addModifier(c, new MagicPowerMod());
                }
            }
        }
    }
    private void degradeMagicPower(){
        degradeMagicPowerInGroup(p.drawPile);
        degradeMagicPowerInGroup(p.hand);
        degradeMagicPowerInGroup(p.discardPile);
        degradeMagicPowerInGroup(p.exhaustPile);
    }
}