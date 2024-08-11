package FrierenMod.utils;

import FrierenMod.cards.magicItems.factors.HighSpeedMovement;
import FrierenMod.cards.magicItems.factors.IceArrows;
import FrierenMod.cards.magicItems.factors.MetallicizFlower;
import FrierenMod.cards.white.HellFireSummoning;
import FrierenMod.cards.white.LightningMagic;
import FrierenMod.cards.whitePurple.OrdinaryOffensiveMagic;

import java.util.HashMap;
import java.util.Map;

public class TopTextHelper {
    private static Map<String,String> initMap(){
        Map<String,String> map = new HashMap<>();
        map.put(OrdinaryOffensiveMagic.ID,"Zoltraak");
        map.put(LightningMagic.ID,"Jutragerme");
        map.put(HellFireSummoning.ID,"Volzanbell");
        map.put(MetallicizFlower.ID,"Jubelade");
        map.put(HighSpeedMovement.ID,"Jilwer");
        map.put(IceArrows.ID,"Nephtear");
        return map;
    }
    public static String getTopTextById(String id){
        Map<String,String> map = initMap();
        return map.get(id);
    }
}
