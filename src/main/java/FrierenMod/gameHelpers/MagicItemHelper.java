package FrierenMod.gameHelpers;

import FrierenMod.ModManager;
import FrierenMod.cards.optionCards.magicItems.AbstractMagicItem;
import FrierenMod.patches.fields.MagicItemBagField;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MagicItemHelper {
    public static final String SAVE_NAME = "magic_item";
    public static void save() {
        ModManager.saveData.putValue(SAVE_NAME, null);
        List<SerializableMagicItem> sfs = new ArrayList<>();
        Gson gson = new Gson();
        for (AbstractCard c : MagicItemBagField.getBag().group) {
            if (c instanceof AbstractMagicItem) {
                sfs.add(new SerializableMagicItem(c.cardID, ((AbstractMagicItem) c).currentSlot));
            }
        }
        JsonArray factorsArray = gson.toJsonTree(sfs).getAsJsonArray();
        ModManager.saveData.putValue(SAVE_NAME, factorsArray);
    }
    public static void load(){
        MagicItemBagField.magicItemBag.set(AbstractDungeon.player, new CardGroup(CardGroup.CardGroupType.UNSPECIFIED));
        if (ModManager.saveData.containsKey(MagicItemHelper.SAVE_NAME)) {
            for (AbstractMagicItem f : MagicItemHelper.getAllItems()) {
                MagicItemBagField.getBag().addToTop(f);
            }
        }
    }

    private static ArrayList<AbstractMagicItem> getAllItems() {
        ArrayList<AbstractMagicItem> fs = new ArrayList<>();
        Gson gson = new Gson();
        Type sfType = new TypeToken<List<SerializableMagicItem>>() {}.getType();
        List<SerializableMagicItem> sfs = gson.fromJson(ModManager.saveData.getValue(SAVE_NAME), sfType);
        for (SerializableMagicItem sf : sfs) {
            AbstractMagicItem f = (AbstractMagicItem) CardLibrary.getCopy(sf.id);
            f.currentSlot = sf.currentSlot;
            fs.add(f);
        }
        return fs;
    }
}
