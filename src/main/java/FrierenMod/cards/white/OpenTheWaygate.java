package FrierenMod.cards.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@AutoAdd.Ignore
public class OpenTheWaygate extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(OpenTheWaygate.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, -2, CardEnums.FRIEREN_CARD, CardRarity.RARE);

    public OpenTheWaygate() {
        super(info);
    }

//    public OpenTheWaygate(CardColor color) {
//        super(ID, -2, color, CardRarity.RARE);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.magicNumber = this.baseMagicNumber = 7;
        this.secondMagicNumber = this.baseSecondMagicNumber = 0;
        this.selfRetain = true;
        this.isTaskCard = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(-1);
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c instanceof AbstractBaseCard && ((AbstractBaseCard) c).isMana) {
            this.flash(FLASH_COLOR);
            this.secondMagicNumber = ++this.baseSecondMagicNumber;
        }
        if (secondMagicNumber >= magicNumber) {
            AbstractPlayer p = AbstractDungeon.player;
            this.superFlash();
            this.secondMagicNumber = this.baseSecondMagicNumber = 0;
            this.addToBot(new SkipEnemiesTurnAction());
            this.addToBot(new ExhaustSpecificCardAction(this, p.hand));
        }
    }
}
