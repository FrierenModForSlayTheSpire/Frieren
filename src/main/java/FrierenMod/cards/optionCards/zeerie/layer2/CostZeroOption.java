package FrierenMod.cards.optionCards.zeerie.layer2;

import FrierenMod.cardMods.CostZeroMod;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CostZeroOption extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(CostZeroOption.class.getSimpleName());
    private AbstractCard currentLegendMagic;
    private int cardAmt;
    public CostZeroOption() {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.SKILL, CardTarget.NONE);
    }

    public CostZeroOption(AbstractCard currentLegendMagic, int cardAmt) {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.SKILL, CardTarget.NONE);
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