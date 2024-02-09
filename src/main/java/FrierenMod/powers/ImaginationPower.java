package FrierenMod.powers;

import FrierenMod.actions.ExhaustMagicPowerTakeTurnsAction;
import FrierenMod.cardMods.RecoverCardDescriptionAfterImaginationPowerMod;
import FrierenMod.cardMods.MagicExhaustTextMod;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.helpers.ModInfo;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ImaginationPower extends AbstractPower {
    public static final String POWER_ID = ModInfo.makeID(ImaginationPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private final AbstractPlayer p = AbstractDungeon.player;

    public ImaginationPower(AbstractCreature owner) {
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

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractFrierenCard && !((AbstractFrierenCard) card).isMagicPower) {
            this.flash();
            if(card.cost > 0){
                this.addToBot(new ExhaustMagicPowerTakeTurnsAction(card.cost));
            }
        }
    }
    @Override
    public void onInitialApplication() {
        update();
    }
    @Override
    public void onDrawOrDiscard() {
        update();
    }
    @Override
    public void atStartOfTurnPostDraw() {
        update();
    }
    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        update();
    }
    public void atEndOfTurn(boolean isPlayer) {
        onPowerLost();
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

    private void upgradeChantActionInGroup(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group) {
            if(c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isChantCard && !((AbstractFrierenCard) c).isChantUpgraded){
                ((AbstractFrierenCard) c).isChantUpgraded = true;
            }
        }
    }
    private void degradeChantActionInGroup(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group) {
            if(c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isChantCard && ((AbstractFrierenCard) c).isChantUpgraded){
                ((AbstractFrierenCard) c).isChantUpgraded = false;
            }
        }
    }
    private void upgradeChantAction(){
        upgradeChantActionInGroup(p.drawPile);
        upgradeChantActionInGroup(p.hand);
        upgradeChantActionInGroup(p.discardPile);
        upgradeChantActionInGroup(p.exhaustPile);
    }
    private void degradeChantAction(){
        degradeChantActionInGroup(p.drawPile);
        degradeChantActionInGroup(p.hand);
        degradeChantActionInGroup(p.discardPile);
        degradeChantActionInGroup(p.exhaustPile);
    }
    private void modifyCardCostInGroup(CardGroup cardGroup){
        for(AbstractCard c:cardGroup.group){
            if(c instanceof AbstractFrierenCard && !((AbstractFrierenCard) c).isMagicPower && !((AbstractFrierenCard)c).isUsingMagicPower && c.cost >= 0){
                CardModifierManager.addModifier(c, new MagicExhaustTextMod(c.cost));
                c.costForTurn = 0;
                ((AbstractFrierenCard) c).isUsingMagicPower = true;
            }
        }
    }
    private void modifyCardCost(){
        modifyCardCostInGroup(p.drawPile);
        modifyCardCostInGroup(p.hand);
        modifyCardCostInGroup(p.discardPile);
        modifyCardCostInGroup(p.exhaustPile);
    }
    private void initCardsInGroup(CardGroup cardGroup){
        for(AbstractCard c:cardGroup.group){
            if(c instanceof AbstractFrierenCard && ((AbstractFrierenCard)c).isUsingMagicPower){
                CardModifierManager.addModifier(c, new RecoverCardDescriptionAfterImaginationPowerMod());
                ((AbstractFrierenCard) c).isUsingMagicPower =false;
            }
        }
    }
    private void initCards(){
        initCardsInGroup(p.drawPile);
        initCardsInGroup(p.hand);
        initCardsInGroup(p.drawPile);
        initCardsInGroup(p.exhaustPile);
    }
    private void update(){
        upgradeChantAction();
        modifyCardCost();
    }
    private void onPowerLost(){
        degradeChantAction();
        initCards();
    }
}