package FrierenMod.actions;

import FrierenMod.cards.optionCards.SerieGrimoire.layer1.Cost0;
import FrierenMod.cards.optionCards.SerieGrimoire.layer1.Cost1;
import FrierenMod.cards.optionCards.SerieGrimoire.layer1.Cost2;
import FrierenMod.cards.optionCards.SerieGrimoire.layer1.Cost3;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public class CustomCardCostAction extends AbstractGameAction {
    private final AbstractGameAction nextAction;
    private final AbstractCard currentLegendMagic;
    private final ArrayList<AbstractCard> layer = new ArrayList<>();
    public CustomCardCostAction(AbstractGameAction newAction, AbstractCard currentLegendMagic){
        this.nextAction = newAction;
        this.currentLegendMagic = currentLegendMagic;
    }
    @Override
    public void update() {
        this.initLayer();
        this.addToBot(new ChooseOneAction(this.layer));
        this.addToBot(this.nextAction);
        this.isDone = true;
    }
    private void initLayer(){
        this.layer.add(new Cost0(this.currentLegendMagic));
        this.layer.add(new Cost1(this.currentLegendMagic));
        this.layer.add(new Cost2(this.currentLegendMagic));
        this.layer.add(new Cost3(this.currentLegendMagic));
    }
}
