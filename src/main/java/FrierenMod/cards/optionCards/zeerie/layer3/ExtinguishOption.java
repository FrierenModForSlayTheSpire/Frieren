package FrierenMod.cards.optionCards.zeerie.layer3;

import FrierenMod.cardMods.ExtinguishMod;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ExtinguishOption extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(ExtinguishOption.class.getSimpleName());
    private AbstractCard currentLegendMagic;
    private int hpAmt;
    public ExtinguishOption() {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.SKILL, CardTarget.ENEMY);
    }
    public ExtinguishOption(AbstractCard currentLegendMagic, int hpAmt) {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.SKILL, CardTarget.ENEMY);
        this.currentLegendMagic = currentLegendMagic;
        this.hpAmt = hpAmt;
        this.magicNumber = this.baseMagicNumber = hpAmt;
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
        CardModifierManager.addModifier(this.currentLegendMagic, new ExtinguishMod(this.hpAmt));
    }
}