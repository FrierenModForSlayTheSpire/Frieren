package FrierenMod.cards.optionCards.zeerie.layer3;

import FrierenMod.cardMods.BlockMod;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BlockOption extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(BlockOption.class.getSimpleName());
    private AbstractCard currentLegendMagic;
    private int blockAmt;
    public BlockOption() {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.SKILL, CardTarget.NONE);
    }

    public BlockOption(AbstractCard currentLegendMagic, int blockAmt) {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.SKILL, CardTarget.NONE);
        this.currentLegendMagic = currentLegendMagic;
        this.blockAmt = blockAmt;
        this.block = this.baseBlock = blockAmt;
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
        CardModifierManager.addModifier(this.currentLegendMagic, new BlockMod(this.blockAmt));
    }
}