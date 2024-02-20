package FrierenMod.gameHelpers;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.white.NormalAttackMagic;
import FrierenMod.cards.white.ThunderMagic;

import java.util.HashMap;
import java.util.Map;

public class TopTextHelper {
    private static Map<String,String> initMap(){
        Map<String,String> map = new HashMap<>();
        map.put(NormalAttackMagic.ID,"Zoltraak");
        map.put(ThunderMagic.ID,"Jutragerme");
        return map;
    }
    public static String getTopTextById(String id){
        Map<String,String> map = initMap();
        return map.get(id);
    }
}
