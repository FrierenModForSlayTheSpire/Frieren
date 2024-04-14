package FrierenMod.cards.optionCards.SerieGrimoire.layer2;

import FrierenMod.cardMods.SelfRetainMod;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SelfRetainOption extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(SelfRetainOption.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.SKILL, CardTarget.NONE);
    public static final CardInfo info2 = new CardInfo(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.SKILL, CardTarget.NONE);
    private AbstractCard currentLegendMagic;
    private int selfRetainAmt;

    public SelfRetainOption() {
        super(info);
    }

    public SelfRetainOption(AbstractCard currentLegendMagic, int selfRetainAmt) {
        super(info2);
        this.currentLegendMagic = currentLegendMagic;
        this.selfRetainAmt = selfRetainAmt;
        this.magicNumber = this.baseMagicNumber = selfRetainAmt;
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
        CardModifierManager.addModifier(this.currentLegendMagic, new SelfRetainMod(this.selfRetainAmt));
    }
}