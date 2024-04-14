package FrierenMod.cards.tempCards;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Himmel extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(Himmel.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, -2, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    public Himmel() {
        super(info);
        this.isEthereal = true;
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }
    @Override
    public void triggerOnEndOfTurnForPlayingCard() {
        Repent c = new Repent();
        if(AbstractDungeon.player.hand.group.contains(this)){
            this.addToBot(new MakeTempCardInHandAction(c,1));
        }
    }
}
