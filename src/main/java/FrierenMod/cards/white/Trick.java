package FrierenMod.cards.white;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.tempCards.HideMagic;
import FrierenMod.helpers.ModInfo;
import FrierenMod.powers.TrickPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FrierenMod.characters.Frieren.Enums.FRIEREN_CARD;

public class Trick extends AbstractFrierenCard {
    public static final String ID = ModInfo.makeID(Trick.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "FrierenModResources/img/cards/LittleTrick_power.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = FRIEREN_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    public Trick() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.cardsToPreview = new HideMagic();
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p,p,new TrickPower(p)));
    }
}
