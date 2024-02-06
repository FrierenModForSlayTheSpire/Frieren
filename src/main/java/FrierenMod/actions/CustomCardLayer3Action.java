package FrierenMod.actions;

import FrierenMod.cards.optionCards.saiLiYe.layer3.BlockOption;
import FrierenMod.cards.optionCards.saiLiYe.layer3.DamageAllOption;
import FrierenMod.cards.optionCards.saiLiYe.layer3.DamageOption;
import FrierenMod.cards.optionCards.saiLiYe.layer3.ExtinguishOption;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public class CustomCardLayer3Action extends AbstractGameAction {
    private final AbstractCard currenLegendMagic;
    private final AbstractGameAction nextAction;
    private final ArrayList<AbstractCard> layer = new ArrayList<>();
    private static final int BLOCK0 = 4;
    private static final int BLOCK1 = 8;
    private static final int BLOCK2 = 10;
    private static final int BLOCK3 = 12;
    private static final int DAMAGE0 = 6;
    private static final int DAMAGE1 = 10;
    private static final int DAMAGE2 = 14;
    private static final int DAMAGE3 = 20;
    private static final int DAMAGE_ALL0 = 4;
    private static final int DAMAGE_ALL1 = 8;
    private static final int DAMAGE_ALL2 = 10;
    private static final int DAMAGE_ALL3 = 15;
    private static final int EXTINGUISH_HP2 = 30;
    private static final int EXTINGUISH_HP3 = 40;
    private static final int RETAIN2 = 1;
    private static final int RETAIN3 = 2;

    public CustomCardLayer3Action(AbstractGameAction nextAction, AbstractCard currenLegendMagic){
        this.nextAction = nextAction;
        this.currenLegendMagic = currenLegendMagic;
    }
    @Override
    public void update() {
        this.initLayer(currenLegendMagic.cost);
        this.addToBot(new ChooseOneAction(layer));
        this.addToBot(nextAction);
        this.isDone = true;
    }
    public void initLayer(int cost){
        switch (cost){
            case 0:
                layer.add(new BlockOption(currenLegendMagic,BLOCK0));
                layer.add(new DamageOption(currenLegendMagic,DAMAGE0));
                layer.add(new DamageAllOption(currenLegendMagic,DAMAGE_ALL0));
                break;
            case 1:
                layer.add(new BlockOption(currenLegendMagic,BLOCK1));
                layer.add(new DamageOption(currenLegendMagic,DAMAGE1));
                layer.add(new DamageAllOption(currenLegendMagic,DAMAGE_ALL1));
                break;
            case 2:
                layer.add(new BlockOption(currenLegendMagic,BLOCK2));
                layer.add(new DamageOption(currenLegendMagic,DAMAGE2));
                layer.add(new DamageAllOption(currenLegendMagic,DAMAGE_ALL2));
                layer.add(new ExtinguishOption(currenLegendMagic,EXTINGUISH_HP2));
                break;
            case 3:
                layer.add(new BlockOption(currenLegendMagic,BLOCK3));
                layer.add(new DamageOption(currenLegendMagic,DAMAGE3));
                layer.add(new DamageAllOption(currenLegendMagic,DAMAGE_ALL3));
                layer.add(new ExtinguishOption(currenLegendMagic,EXTINGUISH_HP3));
                break;
            default:
                break;
        }
    }
}
