package FrierenMod.cards.tempCards;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.city.Byrd;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;

public class BirdCapturingSpell extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(BirdCapturingSpell.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final CardInfo info = new CardInfo(ID, 0, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ENEMY);

    public BirdCapturingSpell() {
        super(info);
        this.exhaust = true;
        this.setDisplayRarity(CardRarity.RARE);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(m.id == "Byrd"|| m.id == "Chosen" || m.id == "AwakenedOne" || m.id=="Cultist") {
            this.addToBot(new ApplyPowerAction(m, p, new StunMonsterPower(m,3), 0));
        }
        else this.addToBot(new TalkAction(true, cardStrings.EXTENDED_DESCRIPTION[0], 1.0F, 2.0F));
    }
}
