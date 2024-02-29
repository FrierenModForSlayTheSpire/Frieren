package FrierenMod.cards.tempCards;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GreenApple extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(GreenApple.class.getSimpleName());
    public GreenApple() {
        super(ID, 0, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.block = this.baseBlock = 3;
        this.exhaust = true;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(2);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 2; i++) {
            this.addToBot(new GainBlockAction(AbstractDungeon.player,this.block));
        }
    }
}
