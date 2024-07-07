package FrierenMod.gameHelpers;

import FrierenMod.ModManager;
import FrierenMod.cards.optionCards.ChantOptions.AbstractMagicFactor;
import FrierenMod.patches.fields.MagicFactorDeckField;
import FrierenMod.utils.Log;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MagicFactorHelper {
    public static final String SAVE_NAME = "magic_factor";
    public static void saveAllFactors() {
        ModManager.saveData.putValue(SAVE_NAME, null);
        List<SerializableMagicFactor> sfs = new ArrayList<>();
        Gson gson = new Gson();
        for (AbstractCard c : MagicFactorDeckField.getDeck().group) {
            if (c instanceof AbstractMagicFactor) {
                sfs.add(new SerializableMagicFactor(c.cardID, ((AbstractMagicFactor) c).currentSlot));
            }
        }
        JsonArray factorsArray = gson.toJsonTree(sfs).getAsJsonArray();
        ModManager.saveData.putValue(SAVE_NAME, factorsArray);
    }

    public static ArrayList<AbstractMagicFactor> getAllFactors() {
        ArrayList<AbstractMagicFactor> fs = new ArrayList<>();
        Gson gson = new Gson();
        Type sfType = new TypeToken<List<SerializableMagicFactor>>() {}.getType();
        List<SerializableMagicFactor> sfs = gson.fromJson(ModManager.saveData.getValue(SAVE_NAME), sfType);
        for (SerializableMagicFactor sf : sfs) {
            AbstractMagicFactor f = (AbstractMagicFactor) CardLibrary.getCard(sf.id);
            f.currentSlot = sf.currentSlot;
            fs.add(f);
            Log.logger.info(f);
        }
        return fs;
    }
}
