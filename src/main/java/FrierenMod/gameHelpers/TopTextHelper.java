package FrierenMod.gameHelpers;

import FrierenMod.cards.white.OrdinaryOffensiveMagic;
import FrierenMod.cards.white.LightningMagic;

import java.util.HashMap;
import java.util.Map;

public class TopTextHelper {
    private static Map<String,String> initMap(){
        Map<String,String> map = new HashMap<>();
        map.put(OrdinaryOffensiveMagic.ID,"Zoltraak");
        map.put(LightningMagic.ID,"Jutragerme");
        return map;
    }
    public static String getTopTextById(String id){
        Map<String,String> map = initMap();
        return map.get(id);
    }
}
