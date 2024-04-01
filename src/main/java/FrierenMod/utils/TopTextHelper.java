package FrierenMod.utils;

import FrierenMod.cards.canAutoAdd.white.HellFireSummoning;
import FrierenMod.cards.whitePurple.OrdinaryOffensiveMagic;
import FrierenMod.cards.canAutoAdd.white.LightningMagic;

import java.util.HashMap;
import java.util.Map;

public class TopTextHelper {
    private static Map<String,String> initMap(){
        Map<String,String> map = new HashMap<>();
        map.put(OrdinaryOffensiveMagic.ID,"Zoltraak");
        map.put(LightningMagic.ID,"Jutragerme");
        map.put(HellFireSummoning.ID,"Volzanbell");
        return map;
    }
    public static String getTopTextById(String id){
        Map<String,String> map = initMap();
        return map.get(id);
    }
}
