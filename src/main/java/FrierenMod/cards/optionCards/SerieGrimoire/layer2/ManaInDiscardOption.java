package FrierenMod.cards.optionCards.SerieGrimoire.layer2;

import FrierenMod.cardMods.ManaInDiscardNumMod;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ManaInDiscardOption extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(ManaInDiscardOption.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.SKILL, CardTarget.NONE);
    public static final CardInfo info2 = new CardInfo(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.SKILL, CardTarget.NONE);
    private AbstractCard currentLegendMagic;

    public ManaInDiscardOption() {
        super(info);
    }

    public ManaInDiscardOption(AbstractCard currentLegendMagic, int magicPowerAmt) {
        super(info2);
        this.currentLegendMagic = currentLegendMagic;
        this.magicNumber = this.baseMagicNumber = magicPowerAmt;
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
        CardModifierManager.addModifier(this.currentLegendMagic, new ManaInDiscardNumMod(this.magicNumber));
    }
}