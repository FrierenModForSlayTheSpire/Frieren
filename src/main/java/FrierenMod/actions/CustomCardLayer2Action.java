package FrierenMod.actions;

import FrierenMod.cards.optionCards.saiLiYe.layer2.DrawOption;
import FrierenMod.cards.optionCards.saiLiYe.layer2.GainRandomCardOption;
import FrierenMod.cards.optionCards.saiLiYe.layer2.MagicPowerOption;
import FrierenMod.cards.optionCards.saiLiYe.layer2.SelfRetainOption;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public class CustomCardLayer2Action extends AbstractGameAction {
    private AbstractCard currenLegendMagic;
    private final AbstractGameAction nextAction;
    private ArrayList<AbstractCard> layer2 = new ArrayList<>();
    private static final int DRAW0 = 2;
    private static final int DRAW1 = 3;
    private static final int DRAW2 = 4;
    private static final int DRAW3 = 5;
    private static final int MAGIC0 = 2;
    private static final int MAGIC1 = 4;
    private static final int MAGIC2 = 6;
    private static final int MAGIC3 = 8;
    private static final int CARD2 = 1;
    private static final int CARD3 = 2;
    private static final int RETAIN2 = 1;
    private static final int RETAIN3 = 2;

    public CustomCardLayer2Action(AbstractGameAction nextAction, AbstractCard currenLegendMagic){
        this.nextAction = nextAction;
        this.currenLegendMagic = currenLegendMagic;
    }
    @Override
    public void update() {
        this.initLayer(currenLegendMagic.cost);
        this.addToBot(new ChooseOneAction(layer2));
        this.addToBot(nextAction);
        this.isDone = true;
    }
    public void initLayer(int cost){
        switch (cost){
            case 0:
                layer2.add(new DrawOption(currenLegendMagic,DRAW0));
                layer2.add(new MagicPowerOption(currenLegendMagic,MAGIC0));
                break;
            case 1:
                layer2.add(new DrawOption(currenLegendMagic,DRAW1));
                layer2.add(new MagicPowerOption(currenLegendMagic,MAGIC1));
                break;
            case 2:
                layer2.add(new DrawOption(currenLegendMagic,DRAW2));
                layer2.add(new MagicPowerOption(currenLegendMagic,MAGIC2));
                layer2.add(new GainRandomCardOption(currenLegendMagic,CARD2));
                layer2.add(new SelfRetainOption(currenLegendMagic,RETAIN2));
                break;
            case 3:
                layer2.add(new DrawOption(currenLegendMagic,DRAW3));
                layer2.add(new MagicPowerOption(currenLegendMagic,MAGIC3));
                layer2.add(new GainRandomCardOption(currenLegendMagic,CARD3));
                layer2.add(new SelfRetainOption(currenLegendMagic,RETAIN3));
                break;
            default:
                break;
        }
    }
}
