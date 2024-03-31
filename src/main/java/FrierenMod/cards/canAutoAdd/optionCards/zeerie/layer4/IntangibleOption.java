package FrierenMod.cards.canAutoAdd.optionCards.zeerie.layer4;

import FrierenMod.cardMods.IntangibleMod;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class IntangibleOption extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(IntangibleOption.class.getSimpleName());
    private AbstractCard currentLegendMagic;
    private int stackAmt;
    public IntangibleOption() {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.POWER, CardTarget.NONE);
    }
    public IntangibleOption(AbstractCard currentLegendMagic, int stackAmt) {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.POWER, CardTarget.NONE);
        this.currentLegendMagic = currentLegendMagic;
        this.stackAmt = stackAmt;
        this.magicNumber = this.baseMagicNumber = stackAmt;
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
        CardModifierManager.addModifier(this.currentLegendMagic, new IntangibleMod(this.stackAmt));
    }
}