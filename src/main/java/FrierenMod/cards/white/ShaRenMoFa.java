package FrierenMod.cards.white;

import FrierenMod.helpers.ModHelper;
import FrierenMod.helpers.LegendMagicHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

import static FrierenMod.Characters.Frieren.Enums.FRIEREN_CARD;
import static FrierenMod.tags.CustomTags.LEGEND_MAGIC;

public class ShaRenMoFa extends CustomCard{
    public static final String ID = ModHelper.makePath(ShaRenMoFa.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "FrierenModResources/img/cards/Strike.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = FRIEREN_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public ShaRenMoFa() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 0;
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(LEGEND_MAGIC);
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.selfRetain = true;
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseMagicNumber = new LegendMagicHelper().getExhaustedMagicPowerNumber();
        this.baseDamage += this.baseMagicNumber;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        this.initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        this.baseMagicNumber = new LegendMagicHelper().getExhaustedMagicPowerNumber();
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.baseMagicNumber;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        this.initializeDescription();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.damage += this.magicNumber;
        this.calculateCardDamage(m);
        for (int i = 0; i < 2; i++) {
            this.addToBot(new DamageAction(
                    m,
                    new DamageInfo(
                            p,
                            this.damage,
                            this.damageTypeForTurn
                    ),
                   AbstractGameAction.AttackEffect.FIRE,true
            ));
        }
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        LegendMagicHelper helper = new LegendMagicHelper();
        if (this.type == AbstractCard.CardType.STATUS && this.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Medical Kit")) {
            return false;
        } else if (this.type == AbstractCard.CardType.CURSE && this.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Blue Candle")) {
            return false;
        } else if (helper.cannotPlayLegendMagic()){
            return false;
        }
        else {
            return this.cardPlayable(m) && this.hasEnoughEnergy();
        }
    }
}
