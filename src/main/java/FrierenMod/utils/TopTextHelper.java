package FrierenMod.utils;

import FrierenMod.cards.optionCards.magicItems.BetaFactor2;
import FrierenMod.cards.optionCards.magicItems.BetaFactor5;
import FrierenMod.cards.white.HellFireSummoning;
import FrierenMod.cards.whitePurple.OrdinaryOffensiveMagic;
import FrierenMod.cards.white.LightningMagic;

import java.util.HashMap;
import java.util.Map;

public class TopTextHelper {
    private static Map<String,String> initMap(){
        Map<String,String> map = new HashMap<>();
        map.put(OrdinaryOffensiveMagic.ID,"Zoltraak");
        map.put(LightningMagic.ID,"Jutragerme");
        map.put(HellFireSummoning.ID,"Volzanbell");
        map.put(BetaFactor2.ID,"Jubelade");
        map.put(BetaFactor5.ID,"Jilwer");
        return map;
    }
    public static String getTopTextById(String id){
        Map<String,String> map = initMap();
        return map.get(id);
    }
}
