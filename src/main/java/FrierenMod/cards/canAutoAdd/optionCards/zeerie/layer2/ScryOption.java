package FrierenMod.cards.canAutoAdd.optionCards.zeerie.layer2;

import FrierenMod.cardMods.ScryMod;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ScryOption extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(ScryOption.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.SKILL, CardTarget.NONE);
    public static final CardInfo info2 = new CardInfo(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.SKILL, CardTarget.NONE);
    private AbstractCard currentLegendMagic;
    private int scryAmt;
    public ScryOption() {
        super(info);
    }

    public ScryOption(AbstractCard currentLegendMagic, int scryAmt) {
        super(info2);
        this.currentLegendMagic = currentLegendMagic;
        this.scryAmt = scryAmt;
        this.magicNumber = this.baseMagicNumber = scryAmt;
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
        CardModifierManager.addModifier(this.currentLegendMagic, new ScryMod(scryAmt));
    }
}