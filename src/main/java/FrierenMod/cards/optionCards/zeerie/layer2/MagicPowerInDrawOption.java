package FrierenMod.cards.optionCards.zeerie.layer2;

import FrierenMod.cardMods.ManaNumMod;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MagicPowerInDrawOption extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(MagicPowerInDrawOption.class.getSimpleName());
    private AbstractCard currentLegendMagic;
    public MagicPowerInDrawOption() {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.SKILL, CardTarget.NONE);
    }
    public MagicPowerInDrawOption(AbstractCard currentLegendMagic, int magicPowerAmt) {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.SKILL, CardTarget.NONE);
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
        CardModifierManager.addModifier(this.currentLegendMagic, new ManaNumMod(this.magicNumber));
    }
}