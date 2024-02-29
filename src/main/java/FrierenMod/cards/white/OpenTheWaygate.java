package FrierenMod.cards.white;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OpenTheWaygate extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(OpenTheWaygate.class.getSimpleName());
    private static final Color FLASH_COLOR = new Color(123.0F/255.0F,236.0F/255.0F,232.0F/255.0F,1.0F);
    public OpenTheWaygate() {
        super(ID, -2, CardRarity.RARE);
        this.magicNumber = this.baseMagicNumber = 7;
        this.secondMagicNumber = this.baseSecondMagicNumber = 0;
        this.selfRetain =true;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(-1);
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if(c instanceof AbstractFrierenCard && ((AbstractFrierenCard)c).isMana){
            this.flash(FLASH_COLOR);
            this.secondMagicNumber = ++this.baseSecondMagicNumber;
        }
        if(secondMagicNumber >= magicNumber){
            AbstractPlayer p = AbstractDungeon.player;
            this.superFlash();
            this.secondMagicNumber = this.baseSecondMagicNumber = 0;
            this.addToBot(new SkipEnemiesTurnAction());
            this.addToBot(new ExhaustSpecificCardAction(this, p.hand));
        }
    }
}
