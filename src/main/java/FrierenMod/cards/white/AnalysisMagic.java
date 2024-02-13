package FrierenMod.cards.white;

import FrierenMod.cardMods.AnalysisMod;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.gameHelpers.LegendMagicHelper;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AnalysisMagic extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(AnalysisMagic.class.getSimpleName());
    private int currentLevel;
    private int currentLevelRequiredNumber;
    private int currentInLevelProgressNumber;
    private static final Color FLASH_COLOR = new Color(123.0F/255.0F,236.0F/255.0F,232.0F/255.0F,1.0F);
    public AnalysisMagic() {
        super(ID, -2, CardRarity.RARE);
        this.block = this.baseBlock = 20;
        this.magicNumber = this.baseMagicNumber = 1;
        this.damage = this.baseDamage = 40;
        this.currentLevel = 3;
        this.currentLevelRequiredNumber = 4;
        this.currentInLevelProgressNumber = 0;
        this.selfRetain = true;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(5);
            this.upgradeMagicNumber(1);
            this.upgradeDamage(5);
            CardModifierManager.addModifier(this,new AnalysisMod(currentLevel,currentLevelRequiredNumber,currentInLevelProgressNumber));
        }
    }
    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        AbstractPlayer p = AbstractDungeon.player;
        if(c instanceof AbstractFrierenCard &&(((AbstractFrierenCard) c).isMagicPower || ((AbstractFrierenCard) c).isChantCard || ((AbstractFrierenCard) c).isLegendMagicCard)){
            switch (currentLevel){
                case 3:
                    if(((AbstractFrierenCard) c).isMagicPower){
                        this.flash(FLASH_COLOR);
                        currentInLevelProgressNumber++;
                        CardModifierManager.addModifier(this, new AnalysisMod(currentLevel,currentLevelRequiredNumber,currentInLevelProgressNumber));
                        if(currentInLevelProgressNumber >= currentLevelRequiredNumber){
                            this.superFlash();
                            currentLevel--;
                            currentInLevelProgressNumber = 0;
                            currentLevelRequiredNumber = 4;
                            this.addToBot(new GainBlockAction(p,this.block));
                            CardModifierManager.addModifier(this, new AnalysisMod(currentLevel,currentLevelRequiredNumber,currentInLevelProgressNumber));
                        }
                        break;
                    }
                case 2:
                    this.currentLevelRequiredNumber = 3;
                    if (((AbstractFrierenCard) c).isChantCard){
                        this.flash(FLASH_COLOR);
                        currentInLevelProgressNumber++;
                        CardModifierManager.addModifier(this, new AnalysisMod(currentLevel,currentLevelRequiredNumber,currentInLevelProgressNumber));
                        if(currentInLevelProgressNumber >= currentLevelRequiredNumber){
                            this.superFlash();
                            currentLevel--;
                            currentInLevelProgressNumber = 0;
                            this.currentLevelRequiredNumber = 3;
                            for (int i = 0; i < this.magicNumber; i++) {
                                AbstractCard rewardCard = new LegendMagicHelper().getRandomCard();
                                rewardCard.costForTurn = 0;
                                this.addToBot(new MakeTempCardInHandAction(rewardCard));
                            }
                            CardModifierManager.addModifier(this, new AnalysisMod(currentLevel,currentLevelRequiredNumber,currentInLevelProgressNumber));
                        }
                    }
                    break;
                case 1:
                    if (((AbstractFrierenCard) c).isLegendMagicCard){
                        this.flash(FLASH_COLOR);
                        currentInLevelProgressNumber++;
                        CardModifierManager.addModifier(this, new AnalysisMod(currentLevel,currentLevelRequiredNumber,currentInLevelProgressNumber));
                        if(currentInLevelProgressNumber >= currentLevelRequiredNumber){
                            this.superFlash();
                            currentInLevelProgressNumber = 0;
                            currentLevel = 3;
                            currentLevelRequiredNumber = 4;
                            this.initializeDescription();
                            this.addToBot(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE, true));
                            this.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
                        }
                    }
                    break;
                default:
                    System.out.println("ERROR!");
                    break;
            }
        }
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }
}
