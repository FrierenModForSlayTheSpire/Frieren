package FrierenMod.cards.purple;

import FrierenMod.actions.MakeManaInHandAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.ActionHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SoSmall extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(SoSmall.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.SKILL, CardEnums.FERN_CARD, CardRarity.UNCOMMON, CardTarget.ENEMY);

    public SoSmall() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.magicNumber = this.baseMagicNumber = 3;
        this.block = baseBlock = 8;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.upgradeBlock(2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ActionHelper.addToBotAbstract(() -> {
            int times = 1;
            if (p.currentHealth >= m.currentHealth)
                times++;
            if (p.currentBlock >= m.currentBlock)
                times++;
            for (int i = 0; i < times; i++) {
                this.addToBot(new MakeManaInHandAction(this.magicNumber));
                this.addToBot(new GainBlockAction(p, this.block));
            }
        });
    }
}

