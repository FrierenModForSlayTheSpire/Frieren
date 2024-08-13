package FrierenMod.cards.white;

import FrierenMod.cardMods.RockGolemSpellMod;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import basemod.AutoAdd;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@Deprecated
@AutoAdd.Ignore
public class RockGolemSpell extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(RockGolemSpell.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, -2, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON);

    public RockGolemSpell() {
        super(info);
    }

//    public RockGolemSpell(CardColor color) {
//        super(ID, -2, color, CardRarity.UNCOMMON);
//    }

    @Override
    public void initSpecifiedAttributes() {
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
        if (c instanceof AbstractBaseCard && (((AbstractBaseCard) c).isMana || ((AbstractBaseCard) c).isChantCard)) {
            if (currentLevel > 0) {
                if (currentLevel % 2 == 0) {
                    if (((AbstractBaseCard) c).isMana) {
                        this.taskProgressIncrease();
                        if (currentInLevelProgressNumber >= currentLevelRequiredNumber) {
                            this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                            this.continueToNextLevel(1);
                        }
                    }
                } else {
                    if (((AbstractBaseCard) c).isChantCard) {
                        this.taskProgressIncrease();
                        if (currentInLevelProgressNumber >= currentLevelRequiredNumber) {
                            this.addToBot(new GainBlockAction(p, this.block));
                            this.continueToNextLevel(2);
                        }
                    }
                }
            } else {
                this.endTask();
            }
        }
    }

    public void initTask() {
        this.currentLevel = 10;
        this.currentLevelRequiredNumber = 2;
        this.currentInLevelProgressNumber = 0;
    }

    @Override
    public void updateDescriptionAndCardImg() {
        CardModifierManager.addModifier(this, new RockGolemSpellMod(currentLevel, currentLevelRequiredNumber, currentInLevelProgressNumber));
    }
}
