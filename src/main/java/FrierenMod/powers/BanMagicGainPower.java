package FrierenMod.powers;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
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

public class BanMagicGainPower extends AbstractPower {
    public static final String POWER_ID = ModInformation.makeID(BanMagicGainPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private final AbstractPlayer p = AbstractDungeon.player;

    public BanMagicGainPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.DEBUFF;
        this.amount = -1;
        String path128 = "FrierenModResources/img/powers/Example84.png";
        String path48 = "FrierenModResources/img/powers/Example32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }
//    @Override
//    public void onInitialApplication() {
//        modifyCanGainMagic();
//    }
//    @Override
//    public void onDrawOrDiscard() {
//        modifyCanGainMagic();
//    }
//    @Override
//    public void atStartOfTurnPostDraw() {
//        modifyCanGainMagic();
//    }
//    @Override
//    public void onAfterCardPlayed(AbstractCard usedCard) {
//        modifyCanGainMagic();
//    }
//    @Override
//    public void atEndOfTurn(boolean isPlayer) {
//        modifyBackCanGainMagic();
//        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
//    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
//    private void modifyCanGainMagicInGroup(CardGroup cardGroup) {
//        for (AbstractCard c : cardGroup.group) {
//            if(c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isMagicSource && ((AbstractFrierenCard) c).canGainMagic){
//                ((AbstractFrierenCard) c).canGainMagic = false;
//            }
//        }
//    }
//    public void modifyCanGainMagic(){
//        modifyCanGainMagicInGroup(p.drawPile);
//        modifyCanGainMagicInGroup(p.hand);
//        modifyCanGainMagicInGroup(p.discardPile);
//        modifyCanGainMagicInGroup(p.exhaustPile);
//    }
//    private void modifyBackCanGainMagicInGroup(CardGroup cardGroup) {
//        for (AbstractCard c : cardGroup.group) {
//            if(c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isMagicSource && !((AbstractFrierenCard) c).canGainMagic){
//                ((AbstractFrierenCard) c).canGainMagic = true;
//            }
//        }
//    }
//    public void modifyBackCanGainMagic(){
//        modifyBackCanGainMagicInGroup(p.drawPile);
//        modifyBackCanGainMagicInGroup(p.hand);
//        modifyBackCanGainMagicInGroup(p.discardPile);
//        modifyBackCanGainMagicInGroup(p.exhaustPile);
//    }
}
