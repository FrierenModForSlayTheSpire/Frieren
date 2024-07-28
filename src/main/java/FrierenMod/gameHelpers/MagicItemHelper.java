package FrierenMod.gameHelpers;

import FrierenMod.ModManager;
import FrierenMod.cards.optionCards.magicItems.AbstractMagicItem;
import FrierenMod.patches.fields.MagicDeckField;
import FrierenMod.patches.fields.RandomField;
import FrierenMod.utils.Log;
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

    public static final String DECK_SAVE_NAME = "magic_deck";
    public static final String MAGIC_ITEM_RNG_COUNT_SAVE_NAME = "magic_item_seed_count";

    public static void save() {
        saveMagicDeck();
        saveMagicItemRngCount();
    }

    public static void load() {
        loadMagicDeck();
        loadRandomNumber();
    }

    private static void loadMagicDeck() {
        MagicDeckField.magicDeck.set(AbstractDungeon.player, new CardGroup(CardGroup.CardGroupType.UNSPECIFIED));
        if (ModManager.saveData.containsKey(MagicItemHelper.DECK_SAVE_NAME)) {
            for (AbstractMagicItem f : MagicItemHelper.getAllMagicItems()) {
                MagicDeckField.getDeck().addToTop(f);
            }
        }
    }

    private static void loadRandomNumber() {
        RandomField.magicItemRng.set(null);
        System.out.println("123");
    }

    private static void saveMagicDeck() {
        ModManager.saveData.putValue(DECK_SAVE_NAME, null);
        List<SerializableMagicItem> sfs = new ArrayList<>();
        Gson gson = new Gson();
        for (AbstractCard c : MagicDeckField.getDeck().group) {
            if (c instanceof AbstractMagicItem) {
                sfs.add(new SerializableMagicItem(c.cardID, ((AbstractMagicItem) c).currentSlot));
            }
        }
        JsonArray factorsArray = gson.toJsonTree(sfs).getAsJsonArray();
        ModManager.saveData.putValue(DECK_SAVE_NAME, factorsArray);
    }

    private static void saveMagicItemRngCount() {
        ModManager.saveData.putValue(MAGIC_ITEM_RNG_COUNT_SAVE_NAME, null);
        if (RandomField.getMagicItemRng() != null) {
            ModManager.saveData.putValue(MAGIC_ITEM_RNG_COUNT_SAVE_NAME, RandomField.getMagicItemRng().counter);
        } else {
            Log.logger.info("WHY NO MAGIC ITEM RNG?");
        }
    }
    public static Integer getMagicItemRngCount() {
        return Integer.valueOf(ModManager.saveData.getValue(MAGIC_ITEM_RNG_COUNT_SAVE_NAME));
    }

    private static ArrayList<AbstractMagicItem> getAllMagicItems() {
        ArrayList<AbstractMagicItem> fs = new ArrayList<>();
        Gson gson = new Gson();
        Type sfType = new TypeToken<List<SerializableMagicItem>>() {
        }.getType();
        List<SerializableMagicItem> sfs = gson.fromJson(ModManager.saveData.getValue(DECK_SAVE_NAME), sfType);
        for (SerializableMagicItem sf : sfs) {
            AbstractMagicItem f = (AbstractMagicItem) CardLibrary.getCopy(sf.id);
            f.currentSlot = sf.currentSlot;
            fs.add(f);
        }
        return fs;
    }
}
