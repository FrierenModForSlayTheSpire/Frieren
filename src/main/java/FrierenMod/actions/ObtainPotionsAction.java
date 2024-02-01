package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import java.util.ArrayList;

public class ObtainPotionsAction extends AbstractGameAction {
    private final ArrayList<AbstractPotion> potions;
    public ObtainPotionsAction(ArrayList<AbstractPotion> potions){
        this.potions = potions;
    }
    @Override
    public void update() {
        for(AbstractPotion potion:this.potions){
            this.addToBot(new ObtainPotionAction(potion));
        }
        this.isDone = true;
    }
}
