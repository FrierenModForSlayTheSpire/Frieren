package FrierenMod.actions;

import FrierenMod.cards.optionCards.saiLiYe.layer1.Cost0;
import FrierenMod.cards.optionCards.saiLiYe.layer1.Cost1;
import FrierenMod.cards.optionCards.saiLiYe.layer1.Cost2;
import FrierenMod.cards.optionCards.saiLiYe.layer1.Cost3;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public class CustomCardCostAction extends AbstractGameAction {
    private AbstractGameAction nextAction;
    private AbstractCard currentLegendMagic;
    private ArrayList<AbstractCard> layer1 = new ArrayList<>();
    private ArrayList<AbstractCard> layer2 = new ArrayList<>();
    private ArrayList<AbstractCard> layer3 = new ArrayList<>();
    private int option;
    private static final int DRAW0 = 2;
    private static final int DRAW1 = 3;
    private static final int DRAW2 = 4;
    private static final int DRAW3 = 5;
    private int draw;
    public CustomCardCostAction(AbstractGameAction newAction, AbstractCard currentLegendMagic){
        this.nextAction = newAction;
        this.currentLegendMagic = currentLegendMagic;
    }
    @Override
    public void update() {
        this.initLayer();
        this.addToBot(new ChooseOneAction(this.layer1));
        this.addToBot(this.nextAction);
        this.isDone = true;
    }
    private void initLayer(){
        this.layer1.add(new Cost0(this.currentLegendMagic));
        this.layer1.add(new Cost1(this.currentLegendMagic));
        this.layer1.add(new Cost2(this.currentLegendMagic));
        this.layer1.add(new Cost3(this.currentLegendMagic));
    }
}
