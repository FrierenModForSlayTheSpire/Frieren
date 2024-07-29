package FrierenMod.actions;

import FrierenMod.cards.optionCards.magicItems.AbstractMagicItem;
import FrierenMod.effects.FastMagicItemObtainEffect;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AddMagicItemToDeckAction extends AbstractGameAction {
    public AbstractCard c;
    public AddMagicItemToDeckAction(AbstractCard c) {
        this.c = c;
    }
    @Override
    public void update() {
        if(c instanceof AbstractMagicItem){
            AbstractDungeon.effectList.add(new FastMagicItemObtainEffect(c));
        }
        this.isDone = true;
    }
}
