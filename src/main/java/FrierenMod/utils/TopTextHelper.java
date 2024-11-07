package FrierenMod.utils;

import FrierenMod.cards.magicItems.factors.BloodSpell;
import FrierenMod.cards.magicItems.factors.HighSpeedMovement;
import FrierenMod.cards.magicItems.factors.IceArrows;
import FrierenMod.cards.magicItems.factors.MetallicizFlower;
import FrierenMod.cards.white.HellFireSummoning;
import FrierenMod.cards.white.LightningMagic;
import FrierenMod.cards.white.OrdinaryOffensiveMagic;

import java.util.HashMap;
import java.util.Map;

public class TopTextHelper {
    private static final Map<String, String> map = initMap();

    private static Map<String, String> initMap() {
        Map<String, String> map = new HashMap<>();
        map.put(OrdinaryOffensiveMagic.ID, "Zoltraak");
        map.put(LightningMagic.ID, "Jutragerme");
        map.put(HellFireSummoning.ID, "Volzanbell");
        map.put(MetallicizFlower.ID, "Jubelade");
        map.put(HighSpeedMovement.ID, "Jilwer");
        map.put(IceArrows.ID, "Nephtear");
        map.put(BloodSpell.ID, "Baruterie");
        return map;
    }

    public static String getTopTextById(String id) {
        return map.get(id);
    }
}
