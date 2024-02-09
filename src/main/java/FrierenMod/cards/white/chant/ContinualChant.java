package FrierenMod.cards.white.chant;

import FrierenMod.actions.ChantAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.helpers.LegendMagicHelper;
import FrierenMod.helpers.ModInfo;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FrierenMod.Characters.Frieren.Enums.FRIEREN_CARD;

public class ContinualChant extends AbstractFrierenCard {
    public static final String ID = ModInfo.makeID(ContinualChant.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "FrierenModResources/img/cards/ContinualChant_skill.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = FRIEREN_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    public ContinualChant() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.chantX = this.baseChantX = 4;
        this.magicNumber = this.baseMagicNumber = 4;
        this.cardsToPreview = new MagicPower();
        this.isChantCard = true;
        this.isLegendMagicCard = true;
        this.isMagicSource = true;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.upgradeChantX(1);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(this.canGainMagic){
            this.addToTop(new MakeTempCardInDrawPileAction(new MagicPower(),this.magicNumber,true,true));
        }
        this.addToBot(new ChantAction(this.isChantUpgraded,this.chantX));
    }
}
