package FrierenMod.cards.optionCards.SerieGrimoire.layer2;

import FrierenMod.cardMods.CostZeroMod;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CostZeroOption extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(CostZeroOption.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.SKILL, CardTarget.NONE);
    public static final CardInfo info2 = new CardInfo(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.SKILL, CardTarget.NONE);
    private AbstractCard currentLegendMagic;
    private int cardAmt;
    public CostZeroOption() {
        super(info);
    }

    public CostZeroOption(AbstractCard currentLegendMagic, int cardAmt) {
        super(info2);
        this.currentLegendMagic = currentLegendMagic;
        this.cardAmt = cardAmt;
        this.magicNumber = this.baseMagicNumber = cardAmt;
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
        CardModifierManager.addModifier(this.currentLegendMagic, (AbstractCardModifier)new CostZeroMod(cardAmt));
    }
}