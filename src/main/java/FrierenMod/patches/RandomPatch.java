package FrierenMod.patches;

import FrierenMod.cards.tempCards.Mana;
import FrierenMod.gameHelpers.SaveFileHelper;
import FrierenMod.patches.fields.RandomField;
import FrierenMod.patches.fields.RandomField2;
import FrierenMod.utils.Log;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;


public class RandomPatch {
    @SpirePatch(clz = AbstractDungeon.class, method = "generateSeeds")
    public static class PatchGenerateSeeds {
        @SpirePostfixPatch
        public static void Postfix() {
            RandomField.magicItemRng.set(new Random(Settings.seed));
            RandomField.magicItemRandomRng.set(new Random(Settings.seed));
            RandomField.manaRandomRng.set(new Random(Settings.seed));
        }
    }

    @SpirePatch(clz = AbstractDungeon.class, method = "loadSeeds")
    public static class PatchLoadSeeds {
        @SpirePostfixPatch
        public static void Postfix() {
            RandomField.magicItemRng.set(new Random(Settings.seed, SaveFileHelper.getMagicItemRngCount()));
            Log.logger.info("Magic Item Seed:{}", RandomField.getMagicItemRng().counter);
            RandomField.magicItemRandomizer.set(SaveFileHelper.getMagicItemRandomizer());
            Log.logger.info("Magic Item Randomizer:{}", RandomField.magicItemRandomizer.get());
            RandomField.magicItemRandomRng.set(new Random(Settings.seed, SaveFileHelper.getMagicItemRandomRngCount()));
            Log.logger.info("Magic Item Seed:{}", RandomField.getMagicItemRandomRng().random);
        }
    }

    @SpirePatch(clz = AbstractDungeon.class, method = "nextRoomTransition", paramtypez = {SaveFile.class})
    public static class PatchNextRoomTransition {
        @SpirePrefixPatch
        public static void Prefix() {
            RandomField.manaRandomRng.set(new Random(Settings.seed + (long) AbstractDungeon.floorNum));
        }
    }

    @SpirePatch(clz = AbstractDungeon.class, method = "reset")
    public static class PatchReset {
        @SpirePostfixPatch
        public static void Postfix() {
            RandomField.magicItemRandomizer.set(RandomField.magicItemBlizzStartOffset);
        }
    }

    @SpirePatch(clz = AbstractDungeon.class, method = "loadSave")
    public static class PatchLoadSave {
        @SpirePostfixPatch
        public static void Postfix() {
            RandomField2.blizzardMagicItemMod.set(SaveFileHelper.getMagicItemChance());
        }
    }
    @SpirePatch(clz = CardGroup.class,method = "addToRandomSpot")
    public static class PatchAddToRandomSpot {
        @SpireInsertPatch(rloc = 0,localvars = {"c"})
        public static SpireReturn<Void> Insert(CardGroup _inst, AbstractCard c){
            if(c instanceof Mana){
                if (_inst.group.isEmpty())
                    _inst.group.add(c);
                else
                    _inst.group.add(RandomField.getManaRandomRng().random(_inst.group.size() - 1), c);
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
}
