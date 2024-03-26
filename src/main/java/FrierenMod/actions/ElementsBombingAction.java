package FrierenMod.actions;

import FrierenMod.cards.tempCards.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

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
    private static ArrayList<AbstractCard> initChantCardPool(){
        ArrayList<AbstractCard> pool = new ArrayList<>();
        pool.add(new Ice());
        pool.add(new Fire());
        pool.add(new Thunder());
        pool.add(new Dark());
        pool.add(new Light());
        return pool;
    }
    public static AbstractCard getRandomCard(){
        ArrayList<AbstractCard> list = initChantCardPool();
        int randomNumber = cardRandomRng.random(100);
        AbstractCard returnCard;
        if (randomNumber < 25) {
            returnCard = list.get(0);
        } else if (randomNumber < 50) {
            returnCard = list.get(1);
        } else if (randomNumber < 75) {
            returnCard = list.get(2);
        } else if (randomNumber < 90) {
            returnCard = list.get(3);
        } else {
            returnCard = list.get(4);
        }
        return returnCard;
    }
}
