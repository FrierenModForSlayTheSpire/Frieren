package FrierenMod.actions;

import FrierenMod.cards.canAutoAdd.optionCards.SerieGrimoire.layer4.DrawOption;
import FrierenMod.cards.canAutoAdd.optionCards.SerieGrimoire.layer4.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class CustomCardLayer4Action extends CustomCardLayerAction {
    private static final int DRAW0 = 1;
    private static final int DRAW1 = 2;
    private static final int DRAW2 = 3;
    private static final int DRAW3 = 5;
    private static final int DEXTERITY0 = 2;
    private static final int DEXTERITY1 = 3;
    private static final int DEXTERITY2 = 4;
    private static final int INTANGIBLE2 = 1;
    private static final int INTANGIBLE3 = 2;
    private static final int VULNERABLE0 = 1;
    private static final int VULNERABLE1 = 2;
    private static final int VULNERABLE2 = 3;
    public CustomCardLayer4Action(AbstractGameAction nextAction, AbstractCard currentLegendMagic,boolean upgraded){
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
                layer.add(new DrawOption(currentLegendMagic,DRAW0));
                layer.add(new DexterityOption(currentLegendMagic,DEXTERITY0));
                layer.add(new VulnerableOption(currentLegendMagic,VULNERABLE0));
                break;
            case 1:
                layer.add(new DrawOption(currentLegendMagic,DRAW1));
                layer.add(new DexterityOption(currentLegendMagic,DEXTERITY1));
                layer.add(new VulnerableOption(currentLegendMagic,VULNERABLE1));
                break;
            case 2:
                layer.add(new DrawOption(currentLegendMagic,DRAW2));
                layer.add(new DexterityOption(currentLegendMagic,DEXTERITY2));
                layer.add(new VulnerableOption(currentLegendMagic,VULNERABLE2));
                layer.add(new IntangibleOption(currentLegendMagic,INTANGIBLE2));
                layer.add(new UpgradeOption(currentLegendMagic));
                break;
            case 3:
                layer.add(new DrawOption(currentLegendMagic,DRAW3));
                layer.add(new IntangibleOption(currentLegendMagic,INTANGIBLE3));
                layer.add(new StrengthOption(currentLegendMagic));
                layer.add(new RemoveDebuffOption(currentLegendMagic));
                layer.add(new UpgradeAllOption(currentLegendMagic));
                break;
            default:
                break;
        }
    }
}
