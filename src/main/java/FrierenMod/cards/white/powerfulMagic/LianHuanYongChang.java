package FrierenMod.cards.white.powerfulMagic;

import FrierenMod.actions.ChantAction;
import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.helpers.ModHelper;
import FrierenMod.helpers.PowerfulMagicHelper;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FrierenMod.Characters.Frieren.Enums.FRIEREN_CARD;
import static FrierenMod.tags.CustomTags.CHANT;
import static FrierenMod.tags.CustomTags.POWERFUL_MAGIC;

public class LianHuanYongChang extends CustomCard{
    public static final String ID = ModHelper.makePath(LianHuanYongChang.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "FrierenModResources/img/cards/Strike.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = FRIEREN_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    public LianHuanYongChang() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 4;
        this.cardsToPreview = new MagicPower();
        this.tags.add(POWERFUL_MAGIC);
        this.tags.add(CHANT);
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToTop(new MakeTempCardInDrawPileAction(new MagicPower(),this.magicNumber,true,true));
        this.addToBot(new ChantAction(p, this.magicNumber));
    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return new PowerfulMagicHelper().canPowerfulMagicUse(this,p,m);
    }
}
