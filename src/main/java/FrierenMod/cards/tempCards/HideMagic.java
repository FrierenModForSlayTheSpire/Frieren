package FrierenMod.cards.tempCards;

import FrierenMod.actions.DrawPileToDiscardPileAction;
import FrierenMod.actions.HandToDiscardPileAction;
import FrierenMod.helpers.ModHelper;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

import static FrierenMod.tags.CustomTags.MAGIC_POWER;

public class HideMagic extends CustomCard{
    public static final String ID = ModHelper.makePath(HideMagic.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); // 从游戏系统读取本地化资源
    private static final String NAME = CARD_STRINGS.NAME; // 读取本地化的名字
    private static final String IMG_PATH = "FrierenModResources/img/cards/HideMagic_skill.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; // 读取本地化的描述
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    public HideMagic() {
        // 为了命名规范修改了变量名。这些参数具体的作用见下方
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.cardsToPreview = new MagicPower();
        this.exhaust = true;
        this.selfRetain = true;
    }
    // 这些方法怎么写，之后再讨论
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false;
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        if (!p.drawPile.isEmpty()) {
            Iterator<AbstractCard> var1 = p.drawPile.group.iterator();
            label21:
            while(true) {
                AbstractCard card;
                do {
                    if (!var1.hasNext()) {
                        break label21;
                    }
                    card = (AbstractCard)var1.next();
                } while(!card.tags.contains(MAGIC_POWER) && !card.freeToPlayOnce);
                this.addToBot(new DrawPileToDiscardPileAction(card));
            }
        }
        if (!p.hand.isEmpty()) {
            Iterator<AbstractCard> var1 = p.hand.group.iterator();

            label22:
            while(true) {
                AbstractCard card;
                do {
                    if (!var1.hasNext()) {
                        break label22;
                    }

                    card = (AbstractCard)var1.next();
                } while(!card.tags.contains(MAGIC_POWER) && !card.freeToPlayOnce);

                this.addToBot(new HandToDiscardPileAction(card));
            }
        }
    }
}
