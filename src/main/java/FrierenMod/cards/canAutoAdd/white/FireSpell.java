package FrierenMod.cards.canAutoAdd.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FireSpell extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(FireSpell.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON);

    public FireSpell() {
        super(info);
    }

//    public FireSpell(CardColor color) {
//        super(ID, 2, CardType.ATTACK, color, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
//    }

    @Override
    public void initSpecifiedAttributes() {
        LittleFire c = new LittleFire();
        if (this.upgraded) {
            c.upgrade();
        }
        this.cardsToPreview = c;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.cardsToPreview.upgrade();
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = 0;
        for (AbstractMonster m2 : AbstractDungeon.getMonsters().monsters) {
            if (!m2.isDeadOrEscaped()) {
                count++;
            }
        }
        this.addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeCopy(), count));
    }
}
