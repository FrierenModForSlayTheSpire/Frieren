package FrierenMod.cards.white;

import FrierenMod.actions.ChantAction;
import FrierenMod.cards.tempCards.Flower;
import FrierenMod.helpers.ChantHelper;
import FrierenMod.helpers.ModHelper;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FrierenMod.Characters.Frieren.Enums.FRIEREN_CARD;
import static FrierenMod.tags.CustomTags.CHANT;

public class HuaTianMoFa extends CustomCard{
    public static final String ID = ModHelper.makePath(HuaTianMoFa.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "FrierenModResources/img/cards/Strike.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = FRIEREN_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    public HuaTianMoFa() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(CHANT);
        Flower c = new Flower();
        if(this.upgraded){
            c.upgraded = true;
        }
        this.cardsToPreview = c;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToTop(new ChantAction(3));
        this.addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeCopy(),1));
    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return new ChantHelper().canChantUse(this,p,m,3);
    }
}