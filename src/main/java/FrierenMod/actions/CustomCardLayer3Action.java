package FrierenMod.actions;

import FrierenMod.cards.canAutoAdd.optionCards.zeerie.layer3.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class CustomCardLayer3Action extends CustomCardLayerAction {
    private static final int BLOCK0 = 4;
    private static final int BLOCK1 = 8;
    private static final int BLOCK2 = 12;
    private static final int BLOCK3 = 16;
    private static final int DAMAGE0 = 6;
    private static final int DAMAGE1 = 10;
    private static final int DAMAGE_THIRD2 = 5;
    private static final int DAMAGE_THIRD3 = 7;
    private static final int DAMAGE_ALL0 = 4;
    private static final int DAMAGE_ALL1 = 8;
    private static final int DAMAGE_ALL2 = 12;
    private static final int DAMAGE_ALL3 = 16;
    private static final int ENERGY1 = 1;
    private static final int ENERGY2 = 2;
    private static final int ENERGY3 = 3;
    private static final int EXTINGUISH_HP2 = 30;
    private static final int EXTINGUISH_HP3 = 40;

    public CustomCardLayer3Action(AbstractGameAction nextAction, AbstractCard currentLegendMagic,boolean upgraded){
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
                layer.add(new BlockOption(currentLegendMagic,BLOCK0));
                layer.add(new DamageOption(currentLegendMagic,DAMAGE0));
                layer.add(new DamageAllOption(currentLegendMagic,DAMAGE_ALL0));
                break;
            case 1:
                layer.add(new BlockOption(currentLegendMagic,BLOCK1));
                layer.add(new DamageOption(currentLegendMagic,DAMAGE1));
                layer.add(new EnergyOption(currentLegendMagic,ENERGY1));
                layer.add(new DamageAllOption(currentLegendMagic,DAMAGE_ALL1));
                break;
            case 2:
                layer.add(new BlockOption(currentLegendMagic,BLOCK2));
                layer.add(new EnergyOption(currentLegendMagic,ENERGY2));
                layer.add(new DamageThirdOption(currentLegendMagic,DAMAGE_THIRD2));
                layer.add(new DamageAllOption(currentLegendMagic,DAMAGE_ALL2));
                layer.add(new ExtinguishOption(currentLegendMagic,EXTINGUISH_HP2));
                break;
            case 3:
                layer.add(new BlockOption(currentLegendMagic,BLOCK3));
                layer.add(new EnergyOption(currentLegendMagic,ENERGY3));
                layer.add(new DamageThirdOption(currentLegendMagic,DAMAGE_THIRD3));
                layer.add(new DamageAllOption(currentLegendMagic,DAMAGE_ALL3));
                layer.add(new ExtinguishOption(currentLegendMagic,EXTINGUISH_HP3));
                break;
            default:
                break;
        }
    }
}
