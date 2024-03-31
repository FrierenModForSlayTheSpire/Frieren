package FrierenMod.cards.optionCards.zeerie.layer4;

import FrierenMod.cardMods.DrawMod;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DrawOption extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(DrawOption.class.getSimpleName());
    private AbstractCard currentLegendMagic;
    private int drawAmt;
    public DrawOption(){
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.SKILL, CardTarget.NONE);
    }

    public DrawOption(AbstractCard currentLegendMagic, int drawAmt) {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.SKILL, CardTarget.NONE);
        this.currentLegendMagic = currentLegendMagic;
        this.drawAmt = drawAmt;
        this.magicNumber = this.baseMagicNumber = drawAmt;
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
        CardModifierManager.addModifier(this.currentLegendMagic, new DrawMod(drawAmt));
    }
}