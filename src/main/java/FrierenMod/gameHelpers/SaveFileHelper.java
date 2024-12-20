package FrierenMod.gameHelpers;

import FrierenMod.ModManager;
import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.patches.PanelPatch;
import FrierenMod.patches.fields.MagicDeckField;
import FrierenMod.patches.fields.RandomField;
import FrierenMod.patches.fields.RandomField2;
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

public class SaveFileHelper {

    public static final String DECK_SAVE_NAME = "magic_deck";
    public static final String MAGIC_ITEM_RNG_COUNT_SAVE_NAME = "magic_item_seed_count";
    public static final String MAGIC_ITEM_RANDOMIZER_SAVE_NAME = "magic_item_randomizer";
    public static final String MAGIC_ITEM_CHANCE_SAVE_NAME = "magic_item_chance";
    public static final String MAGIC_ITEM_RANDOM_COUNT_SAVE_NAME = "magic_item_random_seed_count";
    public static final String FERN_PANEL_MAX_ENERGY_NAME = "fern_panel_max_energy";

    public static void save() {
        saveMagicDeck();
        saveRandomNumber();
        ModManager.saveData.putValue(FERN_PANEL_MAX_ENERGY_NAME, null);
        if (PanelPatch.PanelField.fernPanelMaxEnergy.get(AbstractDungeon.player) != null) {
            ModManager.saveData.putValue(FERN_PANEL_MAX_ENERGY_NAME, PanelPatch.PanelField.fernPanelMaxEnergy.get(AbstractDungeon.player));
        } else {
            ModManager.saveData.putValue(FERN_PANEL_MAX_ENERGY_NAME, 1);
            Log.logger.info("WHY FERN PANEL MAX ENERGY NULL?");
        }
    }

    public static void load() {
        loadMagicDeck();
        loadRandomNumber();
        if (ModManager.saveData.containsKey(FERN_PANEL_MAX_ENERGY_NAME)) {
            PanelPatch.PanelField.fernPanelMaxEnergy.set(AbstractDungeon.player, Integer.valueOf(ModManager.saveData.getValue(FERN_PANEL_MAX_ENERGY_NAME)));
        } else
            PanelPatch.PanelField.fernPanelMaxEnergy.set(AbstractDungeon.player, 1);
    }

    private static void loadMagicDeck() {
        MagicDeckField.magicDeck.set(AbstractDungeon.player, new CardGroup(CardGroup.CardGroupType.UNSPECIFIED));
        if (ModManager.saveData.containsKey(SaveFileHelper.DECK_SAVE_NAME)) {
            for (AbstractMagicItem f : SaveFileHelper.getAllMagicItems()) {
                MagicDeckField.getDeck().addToTop(f);
            }
        }
    }

    private static void loadRandomNumber() {
        RandomField.magicItemRng.set(null);
        RandomField.magicItemRandomizer.set(RandomField.magicItemBlizzStartOffset);
        RandomField2.blizzardMagicItemMod.set(0);
        RandomField.magicItemRandomRng.set(null);
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

    private static void saveRandomNumber() {
        ModManager.saveData.putValue(MAGIC_ITEM_RNG_COUNT_SAVE_NAME, null);
        if (RandomField.getMagicItemRng() != null) {
            ModManager.saveData.putValue(MAGIC_ITEM_RNG_COUNT_SAVE_NAME, RandomField.getMagicItemRng().counter);
        } else {
            Log.logger.info("WHY NO MAGIC ITEM RNG?");
        }
        if (RandomField.getMagicItemRandomizer() != null) {
            ModManager.saveData.putValue(MAGIC_ITEM_RANDOMIZER_SAVE_NAME, RandomField.getMagicItemRandomizer());
        } else {
            Log.logger.info("WHY NO MAGIC ITEM RANDOMIZER?");
        }
        if (RandomField2.getBlizzardMagicItemMod() != null) {
            ModManager.saveData.putValue(MAGIC_ITEM_CHANCE_SAVE_NAME, RandomField2.getBlizzardMagicItemMod());
        } else {
            Log.logger.info("WHY NO MAGIC ITEM MOD?");
        }
        if (RandomField.getMagicItemRandomRng() != null) {
            ModManager.saveData.putValue(MAGIC_ITEM_RANDOM_COUNT_SAVE_NAME, RandomField.getMagicItemRandomRng().counter);
        } else {
            Log.logger.info("WHY NO MAGIC ITEM RANDOM?");
        }
    }

    public static Integer getMagicItemRngCount() {
        return Integer.valueOf(ModManager.saveData.getValue(MAGIC_ITEM_RNG_COUNT_SAVE_NAME));
    }

    public static Integer getMagicItemRandomizer() {
        return Integer.valueOf(ModManager.saveData.getValue(MAGIC_ITEM_RANDOMIZER_SAVE_NAME));
    }

    public static Integer getMagicItemChance() {
        return Integer.valueOf(ModManager.saveData.getValue(MAGIC_ITEM_CHANCE_SAVE_NAME));
    }

    public static Integer getMagicItemRandomRngCount() {
        return Integer.valueOf(ModManager.saveData.getValue(MAGIC_ITEM_RANDOM_COUNT_SAVE_NAME));
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
