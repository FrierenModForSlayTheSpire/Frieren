package FrierenMod.cards.white.chant;

import FrierenMod.actions.ChantAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.helpers.ModInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FrierenMod.characters.Frieren.Enums.FRIEREN_CARD;

public class HolyChant extends AbstractFrierenCard {
    public static final String ID = ModInfo.makeID(HolyChant.class.getSimpleName());
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
        this.isChantCard = true;
        this.chantX = this.baseChantX = 3;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeChantX(1);
            this.selfRetain = true;
        }
    }
    public void use(AbstractPlayer p, AbstractMonster m){
        this.addToBot(new ChantAction(this.chantX));
    }
}
