package FrierenMod.cards.white;

import FrierenMod.actions.MakeManaInHandAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class MultipleOffensiveMagic extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(MultipleOffensiveMagic.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final CardInfo info = new CardInfo(ID, -1, CardType.ATTACK, CardEnums.FRIEREN_CARD, CardRarity.COMMON, CardTarget.ALL_ENEMY);

    public MultipleOffensiveMagic() {
        super(info);
    }

//    public MultipleOffensiveMagic(CardColor color) {
//        super(ID, -1, CardType.ATTACK, color, CardRarity.COMMON, CardTarget.ALL_ENEMY);
//    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.cardsToPreview = new Mana();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int baseDamage = this.energyOnUse + 4;
        int counts = this.upgraded ? this.energyOnUse + 1 : this.energyOnUse;
        int magicNum = this.energyOnUse;
        if (p.hasRelic("Chemical X")) {
            baseDamage += 2;
            counts += 2;
            magicNum += 2;
            p.getRelic("Chemical X").flash();
        }
        this.baseDamage = baseDamage;
        for (int i = 0; i < counts; i++) {
            this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.LIGHTNING));
        }
        this.addToBot(new MakeManaInHandAction(magicNum));
        this.addToBot(new LoseEnergyAction(this.energyOnUse));
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int energy = EnergyPanel.getCurrentEnergy();
        this.baseDamage = energy + 4;
        int times = this.upgraded ? energy + 1 : energy;
        if (AbstractDungeon.player.hasRelic("Chemical X")) {
            times += 2;
            this.baseDamage += 2;
        }
        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.DESCRIPTION;
        if (times > 0)
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + times + cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }

    public void applyPowers() {
        int energy = EnergyPanel.getCurrentEnergy();
        this.baseDamage = energy + 4;
        int times = this.upgraded ? energy + 1 : energy;
        if (AbstractDungeon.player.hasRelic("Chemical X")) {
            times += 2;
            this.baseDamage += 2;
        }
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION;
        if (times > 0)
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + times + cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }
}
