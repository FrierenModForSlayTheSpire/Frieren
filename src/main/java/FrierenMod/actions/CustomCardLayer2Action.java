package FrierenMod.actions;

import FrierenMod.cards.canAutoAdd.optionCards.SerieGrimoire.layer2.*;
import FrierenMod.cards.canAutoAdd.optionCards.SerieGrimoire.layer2.ChantOption;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class CustomCardLayer2Action extends CustomCardLayerAction {
    private static final int MAGIC0 = 2;
    private static final int MAGIC1 = 4;
    private static final int MAGIC2 = 6;
    private static final int MAGIC3 = 8;
    private static final int MAGIC_IN_HAND0 = 2;
    private static final int MAGIC_IN_HAND1 = 3;
    private static final int MAGIC_IN_HAND2 = 4;
    private static final int MAGIC_IN_HAND3 = 5;
    private static final int SCRY0 = 2;
    private static final int SCRY1 = 3;
    private static final int SCRY2 = 4;
    private static final int CHANT0 = 2;
    private static final int CHANT1 = 3;
    private static final int CHANT2 = 4;
    private static final int CARD2 = 1;
    private static final int CARD3 = 2;
    private static final int RETAIN2 = 1;
    private static final int RETAIN3 = 2;
    private static final int COST_ZERO2 = 1;
    private static final int COST_ZERO3 = 2;

    public CustomCardLayer2Action(AbstractGameAction nextAction, AbstractCard currentLegendMagic,boolean upgraded){
        super(nextAction,currentLegendMagic,upgraded);
    }

    @Override
    public void update() {
        super.update();
    }

    public void initLayer(int cost){
        super.initLayer(cost);
        switch (cost){
            case 0:
                layer.add(new ChantOption(currentLegendMagic,CHANT0));
                layer.add(new ManaOption(currentLegendMagic,MAGIC0));
                layer.add(new ManaInHandOption(currentLegendMagic,MAGIC_IN_HAND0));
                layer.add(new ScryOption(currentLegendMagic,SCRY0));
                break;
            case 1:
                layer.add(new ChantOption(currentLegendMagic,CHANT1));
                layer.add(new ManaOption(currentLegendMagic,MAGIC1));
                layer.add(new ManaInHandOption(currentLegendMagic,MAGIC_IN_HAND1));
                layer.add(new ScryOption(currentLegendMagic,SCRY1));
                break;
            case 2:
                layer.add(new ChantOption(currentLegendMagic,CHANT2));
                layer.add(new ManaOption(currentLegendMagic,MAGIC2));
                layer.add(new GainRandomCardOption(currentLegendMagic,CARD2));
                layer.add(new SelfRetainOption(currentLegendMagic,RETAIN2));
                layer.add(new ManaInHandOption(currentLegendMagic,MAGIC_IN_HAND2));
                layer.add(new ScryOption(currentLegendMagic,SCRY2));
                layer.add(new CostZeroOption(currentLegendMagic,COST_ZERO2));
                break;
            case 3:
                layer.add(new ManaOption(currentLegendMagic,MAGIC3));
                layer.add(new GainRandomCardOption(currentLegendMagic,CARD3));
                layer.add(new SelfRetainOption(currentLegendMagic,RETAIN3));
                layer.add(new ManaInHandOption(currentLegendMagic,MAGIC_IN_HAND3));
                layer.add(new CostZeroOption(currentLegendMagic,COST_ZERO3));
                break;
            default:
                break;
        }
    }
}
