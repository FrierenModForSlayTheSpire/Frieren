package FrierenMod.actions;

import FrierenMod.cards.optionCards.saiLiYe.layer4.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public class CustomCardLayer4Action extends AbstractGameAction {
    private AbstractCard currentLegendMagic;
    private final AbstractGameAction nextAction;
    private ArrayList<AbstractCard> layer4 = new ArrayList<>();
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
    public CustomCardLayer4Action(AbstractGameAction nextAction, AbstractCard currenLegendMagic){
        this.nextAction = nextAction;
        this.currentLegendMagic = currenLegendMagic;
    }
    @Override
    public void update() {
        this.initLayer(currentLegendMagic.cost);
        this.addToBot(new ChooseOneAction(layer4));
        this.addToBot(nextAction);
        this.isDone = true;
    }
    public void initLayer(int cost){
        switch (cost){
            case 0:
                layer4.add(new ChantOption(currentLegendMagic,CHANT0));
                layer4.add(new DexterityOption(currentLegendMagic,DEXTERITY0));
                layer4.add(new VulnerableOption(currentLegendMagic,VULNERABLE0));
                break;
            case 1:
                layer4.add(new ChantOption(currentLegendMagic,CHANT1));
                layer4.add(new DexterityOption(currentLegendMagic,DEXTERITY1));
                layer4.add(new VulnerableOption(currentLegendMagic,VULNERABLE1));
                break;
            case 2:
                layer4.add(new ChantOption(currentLegendMagic,CHANT2));
                layer4.add(new DexterityOption(currentLegendMagic,DEXTERITY2));
                layer4.add(new VulnerableOption(currentLegendMagic,VULNERABLE2));
                layer4.add(new IntangibleOption(currentLegendMagic,INTANGIBLE2));
                layer4.add(new UpgradeOption(currentLegendMagic));
                break;
            case 3:
                layer4.add(new IntangibleOption(currentLegendMagic,INTANGIBLE3));
                layer4.add(new StrengthOption(currentLegendMagic));
                layer4.add(new RemoveDebuffOption(currentLegendMagic));
                break;
            default:
                break;
        }
    }
}
