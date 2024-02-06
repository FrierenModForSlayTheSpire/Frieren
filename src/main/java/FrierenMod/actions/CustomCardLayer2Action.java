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
    private final AbstractCard currentLegendMagic;
    private final AbstractGameAction nextAction;
    private final ArrayList<AbstractCard> layer = new ArrayList<>();
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

    public CustomCardLayer2Action(AbstractGameAction nextAction, AbstractCard currentLegendMagic){
        this.nextAction = nextAction;
        this.currentLegendMagic = currentLegendMagic;
    }
    @Override
    public void update() {
        this.initLayer(currentLegendMagic.cost);
        this.addToBot(new ChooseOneAction(layer));
        this.addToBot(nextAction);
        this.isDone = true;
    }
    public void initLayer(int cost){
        switch (cost){
            case 0:
                layer.add(new DrawOption(currentLegendMagic,DRAW0));
                layer.add(new MagicPowerOption(currentLegendMagic,MAGIC0));
                break;
            case 1:
                layer.add(new DrawOption(currentLegendMagic,DRAW1));
                layer.add(new MagicPowerOption(currentLegendMagic,MAGIC1));
                break;
            case 2:
                layer.add(new DrawOption(currentLegendMagic,DRAW2));
                layer.add(new MagicPowerOption(currentLegendMagic,MAGIC2));
                layer.add(new GainRandomCardOption(currentLegendMagic,CARD2));
                layer.add(new SelfRetainOption(currentLegendMagic,RETAIN2));
                break;
            case 3:
                layer.add(new DrawOption(currentLegendMagic,DRAW3));
                layer.add(new MagicPowerOption(currentLegendMagic,MAGIC3));
                layer.add(new GainRandomCardOption(currentLegendMagic,CARD3));
                layer.add(new SelfRetainOption(currentLegendMagic,RETAIN3));
                break;
            default:
                break;
        }
    }
}
