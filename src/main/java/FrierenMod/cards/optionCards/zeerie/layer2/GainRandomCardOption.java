package FrierenMod.cards.optionCards.zeerie.layer2;

import FrierenMod.cardMods.GainRandomCardMod;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.tempCards.CustomLegendMagic;
import FrierenMod.helpers.ModInfo;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GainRandomCardOption extends AbstractFrierenCard {
    public static final String ID = ModInfo.makeID(GainRandomCardOption.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "FrierenModResources/img/cards/GainRandomCardOption_skill.png";
    private static final int COST = -2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;
    private final AbstractCard currentLegendMagic;
    private final int cardAmt;


    public GainRandomCardOption(AbstractCard currentLegendMagic, int cardAmt) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.currentLegendMagic = currentLegendMagic;
        this.cardAmt = cardAmt;
        this.magicNumber = this.baseMagicNumber = cardAmt;
    }
    @Override
    public void upgrade() {
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }

    public void onChoseThisOption() {
        CardModifierManager.addModifier(this.currentLegendMagic, (AbstractCardModifier)new GainRandomCardMod(this.cardAmt));
    }
    public AbstractCard makeCopy() {
        return new GainRandomCardOption(new CustomLegendMagic(),0);
    }
}