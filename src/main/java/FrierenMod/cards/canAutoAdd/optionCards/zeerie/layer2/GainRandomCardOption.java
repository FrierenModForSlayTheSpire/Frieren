package FrierenMod.cards.canAutoAdd.optionCards.zeerie.layer2;

import FrierenMod.cardMods.GainRandomCardMod;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GainRandomCardOption extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(GainRandomCardOption.class.getSimpleName());
    private AbstractCard currentLegendMagic;
    private int cardAmt;
    public GainRandomCardOption() {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.SKILL, CardTarget.NONE);
    }
    public GainRandomCardOption(AbstractCard currentLegendMagic, int cardAmt) {
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
        CardModifierManager.addModifier(this.currentLegendMagic, new GainRandomCardMod(this.cardAmt));
    }
}