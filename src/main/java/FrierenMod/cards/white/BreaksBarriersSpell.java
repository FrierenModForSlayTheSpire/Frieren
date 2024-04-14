package FrierenMod.cards.white;

import FrierenMod.cardMods.BreaksBarriersSpellMod;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CardPoolHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BreaksBarriersSpell extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(BreaksBarriersSpell.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, -2, CardEnums.FRIEREN_CARD, CardRarity.RARE);

    public BreaksBarriersSpell() {
        super(info);
    }

//    public BreaksBarriersSpell(CardColor color) {
//        super(ID, -2, color, CardRarity.RARE);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.block = this.baseBlock = 20;
        this.magicNumber = this.baseMagicNumber = 1;
        this.damage = this.baseDamage = 40;
        this.initTask();
        this.selfRetain = true;
        this.isTaskCard = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(5);
            this.upgradeMagicNumber(1);
            this.upgradeDamage(5);
            CardModifierManager.addModifier(this, new BreaksBarriersSpellMod(currentLevel, currentLevelRequiredNumber, currentInLevelProgressNumber));
        }
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        AbstractPlayer p = AbstractDungeon.player;
        if (c instanceof AbstractBaseCard && (((AbstractBaseCard) c).isMana || ((AbstractBaseCard) c).isChantCard || ((AbstractBaseCard) c).isLegendarySpell)) {
            switch (currentLevel) {
                case 3:
                    if (((AbstractBaseCard) c).isMana) {
                        this.taskProgressIncrease();
                        if (currentInLevelProgressNumber >= currentLevelRequiredNumber) {
                            this.addToBot(new GainBlockAction(p, this.block));
                            this.continueToNextLevel(3);
                        }
                    }
                    break;
                case 2:
                    if (((AbstractBaseCard) c).isChantCard) {
                        this.taskProgressIncrease();
                        if (currentInLevelProgressNumber >= currentLevelRequiredNumber) {
                            for (int i = 0; i < this.magicNumber; i++) {
                                AbstractCard rewardCard = CardPoolHelper.getRandomCard(CardPoolHelper.PoolType.LEGENDARY_SPELL);
                                rewardCard.costForTurn = 0;
                                this.addToBot(new MakeTempCardInHandAction(rewardCard));
                            }
                            this.continueToNextLevel(3);
                        }
                    }
                    break;
                case 1:
                    if (((AbstractBaseCard) c).isLegendarySpell) {
                        this.taskProgressIncrease();
                        if (currentInLevelProgressNumber >= currentLevelRequiredNumber) {
                            this.addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE, true));
                            this.endTask();
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

    public void initTask() {
        this.currentLevel = 3;
        this.currentLevelRequiredNumber = 4;
        this.currentInLevelProgressNumber = 0;
    }

    public void updateDescriptionAndCardImg() {
        CardModifierManager.addModifier(this, new BreaksBarriersSpellMod(currentLevel, currentLevelRequiredNumber, currentInLevelProgressNumber));
    }
}
