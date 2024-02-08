package FrierenMod.cards.white;

import FrierenMod.cardMods.AnalysisMod;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.helpers.LegendMagicHelper;
import FrierenMod.helpers.ModInfo;
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
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FrierenMod.Characters.Frieren.Enums.FRIEREN_CARD;

public class AnalysisMagic extends AbstractFrierenCard {
    public static final String ID = ModInfo.makeID(AnalysisMagic.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "FrierenModResources/img/cards/Strike.png";
    private static final int COST = -2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = FRIEREN_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.NONE;
    private int currentLevel;
    private int currentLevelRequiredNumber;
    private int currentInLevelProgressNumber;
    private static final Color FLASH_COLOR = new Color(123.0F/255.0F,236.0F/255.0F,232.0F/255.0F,1.0F);
    public AnalysisMagic() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
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
        }
    }
    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        AbstractPlayer p = AbstractDungeon.player;
        switch (currentLevel){
            case 3:
                if(c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isMagicPower){
                    this.flash(FLASH_COLOR);
                    currentInLevelProgressNumber++;
                    CardModifierManager.addModifier(this, new AnalysisMod(currentLevel,currentLevelRequiredNumber,currentInLevelProgressNumber,upgraded));
                    if(currentInLevelProgressNumber >= currentLevelRequiredNumber){
                        this.superFlash();
                        currentLevel--;
                        currentInLevelProgressNumber = 0;
                        currentLevelRequiredNumber = 4;
                        this.addToBot(new GainBlockAction(p,this.block));
                        CardModifierManager.addModifier(this, new AnalysisMod(currentLevel,currentLevelRequiredNumber,currentInLevelProgressNumber,upgraded));
                    }
                    break;
                }
            case 2:
                this.currentLevelRequiredNumber = 3;
                if (c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isChantCard){
                    this.flash(FLASH_COLOR);
                    currentInLevelProgressNumber++;
                    CardModifierManager.addModifier(this, new AnalysisMod(currentLevel,currentLevelRequiredNumber,currentInLevelProgressNumber,upgraded));
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
                        CardModifierManager.addModifier(this, new AnalysisMod(currentLevel,currentLevelRequiredNumber,currentInLevelProgressNumber,upgraded));
                    }
                }
                break;
            case 1:
                if (c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isLegendMagicCard){
                    this.flash(FLASH_COLOR);
                    currentInLevelProgressNumber++;
                    CardModifierManager.addModifier(this, new AnalysisMod(currentLevel,currentLevelRequiredNumber,currentInLevelProgressNumber,upgraded));
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
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }
}
