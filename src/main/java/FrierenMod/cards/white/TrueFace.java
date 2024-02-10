package FrierenMod.cards.white;

import FrierenMod.actions.ChantAction;
import FrierenMod.actions.MakeMagicPowerInDiscardAction;
import FrierenMod.actions.MakeMagicPowerInDrawPileAction;
import FrierenMod.actions.MakeMagicPowerInHandAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.helpers.ModInfo;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FrierenMod.Characters.Frieren.Enums.FRIEREN_CARD;

public class TrueFace extends AbstractFrierenCard {
    public static final String ID = ModInfo.makeID(TrueFace.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "FrierenModResources/img/cards/TrueFace_skill.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = FRIEREN_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.NONE;
    public TrueFace() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.chantX = this.baseChantX = 6;
        this.isMagicSource = true;
        this.isChantCard = true;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
            this.exhaust = true;
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChantAction(this.isChantUpgraded,this.chantX));
        this.addToBot(new GainEnergyAction(3));
        this.addToBot(new DrawCardAction(3));
        this.addToBot(new MakeMagicPowerInDrawPileAction(3,canGainMagic));
        this.addToBot(new MakeMagicPowerInHandAction(3,canGainMagic));
        this.addToBot(new MakeMagicPowerInDiscardAction(3,canGainMagic));
    }
}
