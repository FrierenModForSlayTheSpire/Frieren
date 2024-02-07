package FrierenMod.cards.white.chant;

import FrierenMod.actions.ChantAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.helpers.ChantHelper;
import FrierenMod.helpers.ModInfo;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;

import static FrierenMod.Characters.Frieren.Enums.FRIEREN_CARD;


public class CompleteDefendMagic extends AbstractFrierenCard {
    public static final String ID = ModInfo.makeID(CompleteDefendMagic.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "FrierenModResources/img/cards/CompleteDefendMagic_skill.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = FRIEREN_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.NONE;

    public CompleteDefendMagic() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = 10;
        this.isChantCard = true;
        this.magicNumber = this.baseMagicNumber=1;
        this.chantX = this.baseChantX = 3;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(5);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChantAction(this.chantX));
        this.addToBot(new GainBlockAction(p,this.block));
        this.addToBot(new ApplyPowerAction(p, p, new BufferPower(p, this.magicNumber), this.magicNumber));
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return new ChantHelper().canChantUse(this,m,3);
    }
}