package FrierenMod.patches.fields;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;

@SpirePatch(clz = DamageAction.class, method = SpirePatch.CLASS)
public class DamageActionField {
    public static SpireField<DamageAllEnemiesAction> backUpAction = new SpireField<>(() -> null);
}
