package FrierenMod.cards.canAutoAdd.optionCards.zeerie.layer1;

import FrierenMod.cardMods.CostMod;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Cost2 extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(Cost2.class.getSimpleName());
    private AbstractCard currentLegendMagic;
    public Cost2() {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.SKILL, CardTarget.NONE);
    }
    public Cost2(AbstractCard currentLegendMagic) {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.SKILL, CardTarget.NONE);
        this.currentLegendMagic = currentLegendMagic;
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
        CardModifierManager.addModifier(this.currentLegendMagic, new CostMod(2));
    }
}