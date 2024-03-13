package FrierenMod.cards.white;

import FrierenMod.cardMods.RockGolemSpellMod;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RockGolemSpell extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(RockGolemSpell.class.getSimpleName());
    public RockGolemSpell() {
        super(ID, -2, CardRarity.UNCOMMON);
        this.block = this.baseBlock = 5;
        this.damage = this.baseDamage = 6;
        this.initTask();
        this.selfRetain = true;
        this.isTaskCard = true;
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
                        this.taskProgressIncrease();
                        if(currentInLevelProgressNumber >= currentLevelRequiredNumber){
                            this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                            this.continueToNextLevel(1);
                        }
                    }
                }
                else {
                    if (((AbstractFrierenCard) c).isChantCard){
                        this.taskProgressIncrease();
                        if(currentInLevelProgressNumber >= currentLevelRequiredNumber){
                            this.addToBot(new GainBlockAction(p,this.block));
                            this.continueToNextLevel(2);
                        }
                    }
                }
            }else {
                this.endTask();
            }
        }
    }
    public void initTask(){
        this.currentLevel = 10;
        this.currentLevelRequiredNumber = 2;
        this.currentInLevelProgressNumber = 0;
    }
    @Override
    public void updateDescriptionAndCardImg() {
        CardModifierManager.addModifier(this, new RockGolemSpellMod(currentLevel,currentLevelRequiredNumber,currentInLevelProgressNumber));
    }
}
