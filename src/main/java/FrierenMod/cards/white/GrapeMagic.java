package FrierenMod.cards.white;

import FrierenMod.actions.MakeMagicPowerInHandAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.helpers.ModInfo;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FrierenMod.Characters.Frieren.Enums.FRIEREN_CARD;

public class GrapeMagic extends AbstractFrierenCard {
    public static final String ID = ModInfo.makeID(GrapeMagic.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "FrierenModResources/img/cards/Strike_attack.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = FRIEREN_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    public GrapeMagic() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.isMagicSource = true;
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
        this.addToBot(new ExhaustAction(2,false,false,false));
        this.addToBot(new MakeMagicPowerInHandAction(2,canGainMagic));
    }
}
