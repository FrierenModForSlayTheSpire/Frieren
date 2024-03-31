package FrierenMod.cards.canAutoAdd.optionCards.zeerie.layer2;

import FrierenMod.cardMods.ManaInHandNumMod;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MagicPowerInHandOption extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(MagicPowerInHandOption.class.getSimpleName());
    private AbstractCard currentLegendMagic;
    private int magicPowerAmt;
    public MagicPowerInHandOption() {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.SKILL, CardTarget.NONE);
    }
    public MagicPowerInHandOption(AbstractCard currentLegendMagic, int magicPowerAmt) {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.SKILL, CardTarget.NONE);
        this.currentLegendMagic = currentLegendMagic;
        this.magicPowerAmt = magicPowerAmt;
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
        CardModifierManager.addModifier(this.currentLegendMagic, new ManaInHandNumMod(magicPowerAmt));
    }
}