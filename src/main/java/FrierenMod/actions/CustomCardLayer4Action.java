package FrierenMod.actions;

import FrierenMod.cards.optionCards.saiLiYe.layer4.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class CustomCardLayer4Action extends CustomCardLayerAction {
    private static final int CHANT0 = 2;
    private static final int CHANT1 = 3;
    private static final int CHANT2 = 4;
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
    public void initLayer(int cost){
        switch (cost){
            case 0:
                layer.add(new ChantOption(currentLegendMagic,CHANT0));
                layer.add(new DexterityOption(currentLegendMagic,DEXTERITY0));
                layer.add(new VulnerableOption(currentLegendMagic,VULNERABLE0));
                break;
            case 1:
                layer.add(new ChantOption(currentLegendMagic,CHANT1));
                layer.add(new DexterityOption(currentLegendMagic,DEXTERITY1));
                layer.add(new VulnerableOption(currentLegendMagic,VULNERABLE1));
                break;
            case 2:
                layer.add(new ChantOption(currentLegendMagic,CHANT2));
                layer.add(new DexterityOption(currentLegendMagic,DEXTERITY2));
                layer.add(new VulnerableOption(currentLegendMagic,VULNERABLE2));
                layer.add(new IntangibleOption(currentLegendMagic,INTANGIBLE2));
                layer.add(new UpgradeOption(currentLegendMagic));
                break;
            case 3:
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
