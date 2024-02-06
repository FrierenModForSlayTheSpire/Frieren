package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;
import static java.util.Collections.shuffle;

public abstract class CustomCardLayerAction extends AbstractGameAction {
    public AbstractCard currentLegendMagic;
    public AbstractGameAction nextAction;
    public boolean upgraded;
    public int choicesNum;
    public ArrayList<AbstractCard> layer;
    public CustomCardLayerAction(AbstractGameAction nextAction, AbstractCard currentLegendMagic, boolean upgraded){
        this.nextAction = nextAction;
        this.currentLegendMagic = currentLegendMagic;
        this.upgraded = upgraded;
        if(this.upgraded){
            this.choicesNum = 3;
        }else {
            this.choicesNum = 2;
        }
    }

    public CustomCardLayerAction() {

    }

    public void initLayer(int cost){
        this.layer = new ArrayList<>();
    }
    @Override
    public void update() {
        this.initLayer(currentLegendMagic.cost);
        this.addToBot(new ChooseOneAction(this.generateCardChoices(this.layer)));
        this.addToBot(nextAction);
        this.isDone = true;
    }
    public ArrayList<AbstractCard> generateCardChoices(ArrayList<AbstractCard> layer) {
        ArrayList<AbstractCard> choices = new ArrayList<>();
        while(choices.size() != this.choicesNum) {
            boolean dupe = false;
            shuffle(layer, new java.util.Random(cardRandomRng.randomLong()));
            AbstractCard tmp = layer.get(cardRandomRng.random(layer.size() - 1));
            for (AbstractCard c : choices) {
                if (c.cardID.equals(tmp.cardID)) {
                    dupe = true;
                    break;
                }
            }
            if (!dupe) {
                choices.add(tmp);
            }
        }
        return choices;
    }
}
