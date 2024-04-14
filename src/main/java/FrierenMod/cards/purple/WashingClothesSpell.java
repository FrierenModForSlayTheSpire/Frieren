package FrierenMod.cards.purple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.powers.WashingClothesPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WashingClothesSpell extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(WashingClothesSpell.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardType.SKILL, CardEnums.FERN_CARD, CardRarity.RARE, CardTarget.NONE);

    public WashingClothesSpell() {
        super(info);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.isLegendarySpell = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = CombatHelper.cannotPlayLegendarySpell() ? AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy() : AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int exhaustAmt = 0;
        int discardAmt = p.hand.size();
        for (AbstractCard c : p.drawPile.group) {
            this.addToBot(new ExhaustSpecificCardAction(c, p.drawPile));
            exhaustAmt++;
        }
        this.addToBot(new DiscardAction(p, p, p.hand.size(), true));
        this.addToBot(new ApplyPowerAction(p, p, new WashingClothesPower(p, exhaustAmt, discardAmt, this.upgraded ? 2 : 0)));
    }
}

