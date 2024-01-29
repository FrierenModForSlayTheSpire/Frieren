package FrierenMod.cards.white;

import FrierenMod.actions.ChantAction;
import FrierenMod.helpers.ChantHelper;
import FrierenMod.helpers.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

import static FrierenMod.Characters.Frieren.Enums.FRIEREN_CARD;
import static FrierenMod.tags.CustomTags.CHANT;

public class HolyChant extends CustomCard{
    public static final String ID = ModHelper.makePath(HolyChant.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "FrierenModResources/img/cards/Strike.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = FRIEREN_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    public HolyChant() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 3;
        this.tags.add(CHANT);
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
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChantAction(this.magicNumber));
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return new ChantHelper().canChantUse(this,m,this.magicNumber);
    }
}
