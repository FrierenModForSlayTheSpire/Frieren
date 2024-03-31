package FrierenMod.cards.white;

import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Defend_Frieren extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(Defend_Frieren.class.getSimpleName());

    public Defend_Frieren() {
        super(ID, 1, CardType.SKILL, CardEnums.FRIEREN_CARD, CardRarity.BASIC, CardTarget.SELF);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.block = baseBlock = 5;
        this.tags.add(CardTags.STARTER_DEFEND);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
    }
}
