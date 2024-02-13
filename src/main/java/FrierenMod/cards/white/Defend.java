package FrierenMod.cards.white;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Defend extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(Defend.class.getSimpleName());
    public Defend() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
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
            this.addToBot(new GainBlockAction(p,p,this.block));
    }
}
