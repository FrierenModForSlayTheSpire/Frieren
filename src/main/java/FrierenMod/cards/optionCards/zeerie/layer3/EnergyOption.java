package FrierenMod.cards.optionCards.zeerie.layer3;

import FrierenMod.cardMods.EnergyMod;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EnergyOption extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(EnergyOption.class.getSimpleName());
    private AbstractCard currentLegendMagic;
    private int energyAmt;
    public EnergyOption() {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.SKILL, CardTarget.NONE);
    }
    public EnergyOption(AbstractCard currentLegendMagic, int energyAmt) {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.SKILL, CardTarget.NONE);
        this.currentLegendMagic = currentLegendMagic;
        this.energyAmt = energyAmt;
        this.magicNumber = this.baseMagicNumber = energyAmt;
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
        CardModifierManager.addModifier(this.currentLegendMagic, new EnergyMod(this.energyAmt));
    }
}