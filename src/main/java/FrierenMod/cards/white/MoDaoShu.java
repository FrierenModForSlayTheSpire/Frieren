package FrierenMod.cards.white;

import FrierenMod.helpers.ModHelper;
import FrierenMod.helpers.LegendMagicHelper;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FrierenMod.Characters.Frieren.Enums.FRIEREN_CARD;

public class MoDaoShu extends CustomCard{
    public static final String ID = ModHelper.makePath(MoDaoShu.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "FrierenModResources/img/cards/Strike.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = FRIEREN_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    public MoDaoShu() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
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
        LegendMagicHelper helper = new LegendMagicHelper();
        if(!this.upgraded){
            for(AbstractCard c : helper.getRandomCards(2)){
                this.addToBot(new MakeTempCardInHandAction(c));
            }
        }
        else {
            for(AbstractCard c : helper.getRandomCards(2)){
                c.setCostForTurn(0);
                this.addToBot(new MakeTempCardInHandAction(c));
            }
        }
    }
}
