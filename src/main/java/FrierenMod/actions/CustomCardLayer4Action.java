package FrierenMod.actions;

import FrierenMod.cards.optionCards.saiLiYe.layer4.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public class CustomCardLayer4Action extends AbstractGameAction {
    private AbstractCard currenLegendMagic;
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
        this.currenLegendMagic = currenLegendMagic;
    }
    @Override
    public void update() {
        this.initLayer(currenLegendMagic.cost);
        this.addToBot(new ChooseOneAction(layer4));
        this.addToBot(nextAction);
        this.isDone = true;
    }
    public void initLayer(int cost){
        switch (cost){
            case 0:
                layer4.add(new ChantOption(currenLegendMagic,CHANT0));
                layer4.add(new DexterityOption(currenLegendMagic,DEXTERITY0));
                layer4.add(new VulnerableOption(currenLegendMagic,VULNERABLE0));
                break;
            case 1:
                layer4.add(new ChantOption(currenLegendMagic,CHANT1));
                layer4.add(new DexterityOption(currenLegendMagic,DEXTERITY1));
                layer4.add(new VulnerableOption(currenLegendMagic,VULNERABLE1));
                break;
            case 2:
                layer4.add(new ChantOption(currenLegendMagic,CHANT2));
                layer4.add(new DexterityOption(currenLegendMagic,DEXTERITY2));
                layer4.add(new VulnerableOption(currenLegendMagic,VULNERABLE2));
                layer4.add(new IntangibleOption(currenLegendMagic,INTANGIBLE2));
                layer4.add(new UpgradeOption(currenLegendMagic));
                break;
            case 3:
                layer4.add(new IntangibleOption(currenLegendMagic,INTANGIBLE3));
                layer4.add(new StrengthOption(currenLegendMagic));
                layer4.add(new RemoveDebuffOption(currenLegendMagic));
                break;
            default:
                break;
        }
    }
}
