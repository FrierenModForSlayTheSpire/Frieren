package FrierenMod.patches;

import FrierenMod.actions.ExhaustMagicPowerTakeTurnsAction;
import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

@SpirePatch(clz= AbstractPlayer.class,method = "useCard")
public class PatchUseCard{
    @SpireInstrumentPatch
    public static ExprEditor Instrument(){
        return new ExprEditor(){

            @Override
            public void edit(MethodCall m) throws CannotCompileException {
                if(isMethodCalled(m)){

                    m.replace("{" +
                            "if(" + PatchUseCard.class.getName() + ".check($0) && this.hasPower(\"FrierenMod:MagicInsteadOfCostPower\")){"+
                            PatchUseCard.class.getName() + ".cal(c);" +
                            "}else{"+
                            "$_=$proceed($$);" +
                            "}"+
                            "}");

                }
            }
            private boolean isMethodCalled(MethodCall m){
                return "use".equals(m.getMethodName());
            }
        };
    }
    public static boolean check(Object o){
        return o instanceof EnergyManager;
    }
    public static void cal(AbstractCard c){
        if(c.cost >= 0){
            AbstractDungeon.actionManager.addToBottom(new ExhaustMagicPowerTakeTurnsAction(c.cost));
        }
    }
}