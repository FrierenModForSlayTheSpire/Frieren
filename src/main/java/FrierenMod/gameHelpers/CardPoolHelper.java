package FrierenMod.gameHelpers;

import FrierenMod.cards.white.*;
import FrierenMod.cards.whitePurple.OrdinaryOffensiveMagic;
import FrierenMod.cards.whitePurple.RapidChant;
import FrierenMod.cards.whitePurple.ShavedIceSpell;
import FrierenMod.utils.Config;
import FrierenMod.utils.Log;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

;import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;

public class CardPoolHelper {
//    public static ArrayList<AbstractCard> getFrierenCardPool(){
//        ArrayList<AbstractCard> retVal = new ArrayList<>();
//        retVal.add(new Strike_Frieren());
//        retVal.add(new Defend_Frieren());
//        retVal.add(new AccessoriesSpell());
//        retVal.add(new AirSlash());
//        retVal.add(new ApexMagic());
//        retVal.add(new Apprehend());
//        retVal.add(new BabySleeping());
//        retVal.add(new BackFlow());
//        retVal.add(new BreaksBarriersSpell());
//        retVal.add(new ClearMind());
//        retVal.add(new Confrontation());
//        retVal.add(new ContinualChant());
//        retVal.add(new ContinuousShooting());
//        retVal.add(new DefensiveMagic());
//        retVal.add(new Disperse());
//        retVal.add(new ElementsBombing());
//        retVal.add(new Famehameha());
//        retVal.add(new FinalChant());
//        retVal.add(new FireSpell());
//        retVal.add(new Flow());
//        retVal.add(new FlowerFieldSpell());
//        retVal.add(new FlyingMagic());
//        retVal.add(new FolkGrimoire());
//        retVal.add(new Free());
//        retVal.add(new FullAhead());
//        retVal.add(new GrandCross());
//        retVal.add(new GrapeSpell());
//        retVal.add(new HalfTheSky());
//        retVal.add(new HellFireSummoning());
//        retVal.add(new Imagination());
//        retVal.add(new Kiss());
//        retVal.add(new LightningMagic());
//        retVal.add(new LittleFire());
//        retVal.add(new LockTarget());
//        retVal.add(new LureTheEnemyInDeep());
//        retVal.add(new MageStyleChoke());
//        retVal.add(new MageStyleFinisher());
//        retVal.add(new ManaBarricade());
//        retVal.add(new ManaCollapse());
//        retVal.add(new ManaDetection());
//        retVal.add(new ManaExpansion());
//        retVal.add(new ManaParcel());
//        retVal.add(new MoldSpell());
//        retVal.add(new MultipleOffensiveMagic());
//        retVal.add(new OilSpell());
//        retVal.add(new OpenTheWaygate());
//        retVal.add(new Outpouring());
//        retVal.add(new PajamasForm());
//        retVal.add(new PancakeSpell());
//        retVal.add(new PerfectDefensiveMagic());
//        retVal.add(new Perpetual());
//        retVal.add(new PreciseChant());
//        retVal.add(new PreparedPosture());
//        retVal.add(new Recall());
//        retVal.add(new Recycle());
//        retVal.add(new RedAppleSpell());
//        retVal.add(new RestraintMagic());
//        retVal.add(new RingletForm());
//        retVal.add(new RitesPreparation());
//        retVal.add(new RockGolemSpell());
//        retVal.add(new RustCleanMagic());
//        retVal.add(new SayThinkingSpell());
//        retVal.add(new SeeThroughPhantom());
//        retVal.add(new SerieGrimoire());
//        retVal.add(new Simmering());
//        retVal.add(new SwampSpell());
//        retVal.add(new TimeConcept());
//        retVal.add(new TongueTwisterSpell());
//        retVal.add(new Trick());
//        retVal.add(new TrueColours());
//        return retVal;
//    }
//    public static ArrayList<AbstractCard> getFernCardPool(){
//        ArrayList<AbstractCard> retVal = new ArrayList<>();
//        retVal.add(new Strike_Fern());
//        retVal.add(new Defend_Fern());
//        return retVal;
//    }
    public static ArrayList<AbstractCard> getBaseFrierenFernCardPool(){
        ArrayList<AbstractCard> retVal = new ArrayList<>();
        if(Config.FERN_ENABLE){
            retVal.add(new OrdinaryOffensiveMagic(OrdinaryOffensiveMagic.info2));
            retVal.add(new RapidChant(RapidChant.info2));
            retVal.add(new ShavedIceSpell(ShavedIceSpell.info2));
        }
        retVal.add(new OrdinaryOffensiveMagic(OrdinaryOffensiveMagic.info));
        retVal.add(new RapidChant(RapidChant.info));
        retVal.add(new ShavedIceSpell(ShavedIceSpell.info));
        return retVal;
    }
    public static ArrayList<AbstractCard> getChantCardPool(){
        ArrayList<AbstractCard> retVal = new ArrayList<>();
        retVal.add(new DefensiveMagic());
        retVal.add(new RapidChant());
        retVal.add(new ContinualChant());
        retVal.add(new FlowerFieldSpell());
        retVal.add(new PerfectDefensiveMagic());
        retVal.add(new FinalChant());
        retVal.add(new PreciseChant());
        retVal.add(new TrueColours());
        retVal.add(new LureTheEnemyInDeep());
        retVal.add(new ManaBarricade());
        return retVal;
    }
    public static ArrayList<AbstractCard> getLegendarySpellCardPool(){
        ArrayList<AbstractCard> retVal = new ArrayList<>();
        retVal.add(new FlyingMagic());
        retVal.add(new ContinualChant());
        retVal.add(new AccessoriesSpell());
        retVal.add(new OilSpell());
        retVal.add(new LightningMagic());
        retVal.add(new Famehameha());
        retVal.add(new FairyTaleEnd());
        retVal.add(new RustCleanMagic());
        return retVal;
    }
    public static AbstractCard getRandomCard(PoolType type) {
        ArrayList<AbstractCard> list;
        switch (type){
            case CHANT:
                list = getChantCardPool();
                break;
            case LEGENDARY_SPELL:
                list = getLegendarySpellCardPool();
                break;
            default:
                list = null;
                Log.logger.info("WTF?");
                break;
        }
        return list.get(cardRandomRng.random(list.size() - 1));
    }
    public enum PoolType{
        CHANT,
        LEGENDARY_SPELL
    }
}
