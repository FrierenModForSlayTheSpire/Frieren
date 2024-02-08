package FrierenMod.cards.optionCards.zeerie.layer3;

import FrierenMod.cardMods.BlockMod;
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

public class BlockOption extends AbstractFrierenCard {
    public static final String ID = ModInfo.makeID(BlockOption.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "FrierenModResources/img/cards/Strike.png";
    private static final int COST = -2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;
    private final AbstractCard currentLegendMagic;
    private final int blockAmt;


    public BlockOption(AbstractCard currentLegendMagic, int blockAmt) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.currentLegendMagic = currentLegendMagic;
        this.blockAmt = blockAmt;
        this.block = this.baseBlock = blockAmt;
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
        CardModifierManager.addModifier(this.currentLegendMagic, (AbstractCardModifier)new BlockMod(this.blockAmt));
    }
    public AbstractCard makeCopy() {
        return new BlockOption(new CustomLegendMagic(),0);
    }
}