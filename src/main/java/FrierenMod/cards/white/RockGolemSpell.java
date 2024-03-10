package FrierenMod.cards.white;

import FrierenMod.cardMods.RockGolemSpellMod;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RockGolemSpell extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(RockGolemSpell.class.getSimpleName());
    private int currentLevel;
    private int currentLevelRequiredNumber;
    private int currentInLevelProgressNumber;
    private static final Color FLASH_COLOR = new Color(123.0F/255.0F,236.0F/255.0F,232.0F/255.0F,1.0F);
    public RockGolemSpell() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.block = this.baseBlock = 5;
        this.damage = this.baseDamage = 6;
        this.initTask();
        this.selfRetain = true;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(2);
            this.upgradeDamage(2);
        }
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }
    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        AbstractPlayer p = AbstractDungeon.player;
        if(c instanceof AbstractFrierenCard &&(((AbstractFrierenCard) c).isMana || ((AbstractFrierenCard) c).isChantCard)){
            if(currentLevel > 0){
                if(currentLevel % 2 == 0){
                    if(((AbstractFrierenCard) c).isMana){
                        this.flash(FLASH_COLOR);
                        currentInLevelProgressNumber++;
                        CardModifierManager.addModifier(this, new RockGolemSpellMod(currentLevel,currentLevelRequiredNumber,currentInLevelProgressNumber));
                        if(currentInLevelProgressNumber >= currentLevelRequiredNumber){
                            this.superFlash();
                            currentLevel--;
                            currentInLevelProgressNumber = 0;
                            currentLevelRequiredNumber = 1;
                            this.addToBot(new AttackDamageRandomEnemyAction(c, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                            CardModifierManager.addModifier(this, new RockGolemSpellMod(currentLevel,currentLevelRequiredNumber,currentInLevelProgressNumber));
                        }
                    }
                }
                else {
                    if (((AbstractFrierenCard) c).isChantCard){
                        this.flash(FLASH_COLOR);
                        currentInLevelProgressNumber++;
                        CardModifierManager.addModifier(this, new RockGolemSpellMod(currentLevel,currentLevelRequiredNumber,currentInLevelProgressNumber));
                        if(currentInLevelProgressNumber >= currentLevelRequiredNumber){
                            this.superFlash();
                            currentLevel--;
                            currentInLevelProgressNumber = 0;
                            this.currentLevelRequiredNumber = 2;
                            this.addToBot(new GainBlockAction(p,this.block));
                            CardModifierManager.addModifier(this, new RockGolemSpellMod(currentLevel,currentLevelRequiredNumber,currentInLevelProgressNumber));
                        }
                    }
                }
            }else {
                this.initTask();
                this.initializeDescription();
                this.addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
            }
        }
    }
    private void initTask(){
        this.initializeDescription();
        this.currentLevel = 10;
        this.currentLevelRequiredNumber = 2;
        this.currentInLevelProgressNumber = 0;
    }
}
