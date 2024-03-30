package FrierenMod.actions;

import FrierenMod.cards.tempCards.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;

public class ElementsBombingAction extends AbstractGameAction {
    private final boolean upgraded;
    public ElementsBombingAction(boolean upgraded){
        this.upgraded = upgraded;
    }
    @Override
    public void update() {
        int theSize = AbstractDungeon.player.hand.size();
        int i;
        if (this.upgraded) {
            for (i = 0; i < theSize; i++) {
                AbstractCard s = getRandomCard().makeCopy();
                s.upgrade();
                addToTop(new MakeTempCardInHandAction(s, 1));
            }
        } else {
            for (i = 0; i < theSize; i++) {
                addToTop(new MakeTempCardInHandAction(getRandomCard(), 1));
            }
        }
        for(i = 0; i < theSize; ++i) {
            if (Settings.FAST_MODE) {
                addToTop(new ExhaustAction(1, true, true, false, Settings.ACTION_DUR_XFAST));
            } else {
                addToTop(new ExhaustAction(1, true, true));
            }
        }
        this.isDone = true;
    }
    public static AbstractCard getRandomCard(){
        int randomNumber = cardRandomRng.random(100);
        AbstractCard returnCard;
        if (randomNumber < 25) {
            returnCard = new Ice();
        } else if (randomNumber < 50) {
            returnCard = new Fire();
        } else if (randomNumber < 75) {
            returnCard = new Thunder();
        } else if (randomNumber < 90) {
            returnCard = new Dark();
        } else {
            returnCard = new Light();
        }
        return returnCard;
    }
}
